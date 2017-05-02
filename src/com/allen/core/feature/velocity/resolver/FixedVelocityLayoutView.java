package com.allen.core.feature.velocity.resolver;

//import com.hundsun.jresplus.web.contain.async.AsynchronousContain;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.springframework.web.servlet.view.velocity.VelocityToolboxView;

import com.hundsun.jresplus.web.contain.async.AsynchronousContain;

public class FixedVelocityLayoutView extends VelocityToolboxView
{

    public FixedVelocityLayoutView()
    {
        layoutUrl = "layout/default.vm";
        layoutKey = "layout";
        screenContentKey = "screen_content";
        layoutPrefix = "layout";
        layoutPrefixChars = layoutPrefix.toCharArray();
        defaultLayoutName = "default.vm";
        defaultLayoutNameChars = defaultLayoutName.toCharArray();
    }

    public void setScreenPrefix(String prefix)
    {
        screenPrefixChars = prefix.toCharArray();
    }

    public void setLayoutUrl(String layoutUrl)
    {
        this.layoutUrl = layoutUrl;
        layoutPrefix = layoutUrl.substring(0, layoutUrl.indexOf("/") + 1);
        layoutPrefixChars = layoutPrefix.toCharArray();
        defaultLayoutName = layoutUrl.substring(layoutUrl.indexOf("/") + 1);
        defaultLayoutNameChars = defaultLayoutName.toCharArray();
    }

    public void setLayoutKey(String layoutKey)
    {
        this.layoutKey = layoutKey;
    }

    public void setScreenContentKey(String screenContentKey)
    {
        this.screenContentKey = screenContentKey;
    }

    public boolean checkResource(Locale locale)
        throws Exception
    {
        return checkResource(getUrl());
    }

    protected boolean checkResource(String url)
        throws Exception
    {
        return getVelocityEngine().resourceExists(url);
    }

    private boolean isInContain(Context context)
    {
        Integer cCount = (Integer)context.get("_iContain");
        return cCount != null && cCount.intValue() > 0;
    }

    private boolean isInAsyncContain()
    {
        return AsynchronousContain.isAsyncConext();
    }

    protected void doRender(Context context, HttpServletResponse response)
        throws Exception
    {
        if(isInAsyncContain())
        {
            if(logger.isDebugEnabled())
                logger.debug((new StringBuilder("in async contain render for:")).append(getUrl()).toString());
            renderCotainContent(context, response);
            return;
        }
        if(isInContain(context))
        {
            if(logger.isDebugEnabled())
                logger.debug((new StringBuilder("in contain render for:")).append(getUrl()).toString());
            renderCotainContent(context, response);
            return;
        }
        renderScreenContent(context);
        String layoutUrlToUse = (String)context.get(layoutKey);
        if(StringUtils.isNotBlank(layoutUrlToUse))
        {
            if(logger.isDebugEnabled())
                logger.debug((new StringBuilder("Screen content template has requested layout [")).append(layoutUrlToUse).append("]").toString());
            mergeTemplate(getTemplate(layoutUrlToUse), context, response);
        } else
        if(layoutUrlToUse == null)
            mergeTemplate(findLayoutTemplate(), context, response);
        else
            ((CharArrayWriterWrapper)screenLocal.get()).writeTo(response.getWriter());
//        break MISSING_BLOCK_LABEL_246;
//        Exception exception;
//        exception;
//        ((CharArrayWriterWrapper)screenLocal.get()).reset();
//        throw exception;
        ((CharArrayWriterWrapper)screenLocal.get()).reset();
        return;
    }

    protected Template findLayoutTemplate()
        throws Exception
    {
        Template cached = (Template)layoutTemplateCache.get(getUrl());
        if(cached == null)
        {
            cached = searchLayoutTemplate();
            layoutTemplateCache.put(getUrl(), cached);
        }
        return cached;
    }

    protected final Template searchLayoutTemplate()
        throws Exception
    {
        boolean debug = logger.isDebugEnabled();
        LayoutFinder finder = new LayoutFinder(getUrl(), layoutPrefixChars, screenPrefixChars, defaultLayoutNameChars);
        String url = finder.getSameNameLayoutUrl();
        if(checkResource(url))
        {
            if(debug)
                logger.debug((new StringBuilder("Find layout template [")).append(url).append("] for:").append(getUrl()).toString());
            return getTemplate(url);
        }
        while((url = finder.getLayoutUrl()) != null) 
            if(checkResource(url))
            {
                if(debug)
                    logger.debug((new StringBuilder("Find layout template [")).append(url).append("] for:").append(getUrl()).toString());
                return getTemplate(url);
            }
        return getTemplate(layoutUrl);
    }

    private void renderScreenContent(Context velocityContext)
        throws Exception
    {
        if(logger.isDebugEnabled())
            logger.debug((new StringBuilder("Rendering screen content template [")).append(getUrl()).append("]").toString());
        CharArrayWriterWrapper writer = (CharArrayWriterWrapper)screenLocal.get();
        Template screenContentTemplate = getTemplate(getUrl());
        screenContentTemplate.merge(velocityContext, writer.getWriter());
        velocityContext.put(screenContentKey, writer);
    }

    private void renderCotainContent(Context velocityContext, HttpServletResponse response)
        throws Exception
    {
        if(logger.isDebugEnabled())
            logger.debug((new StringBuilder("Rendering contain content template [")).append(getUrl()).append("]").toString());
        Template cotainContentTemplate = getTemplate(getUrl());
        cotainContentTemplate.merge(velocityContext, response.getWriter());
    }

    protected void mergeTemplate(Template template, Context context, HttpServletResponse response)
        throws Exception
    {
        super.mergeTemplate(template, context, response);
    }

    protected boolean isCacheTemplate()
    {
        return false;
    }

    public void setLayoutTemplateCache(Map layoutTemplateCache)
    {
        this.layoutTemplateCache = layoutTemplateCache;
    }

    public static final String Seq = "/";
    public static final String DefaultLayoutPrefix = "layout/";
    public static final String DefaultLayoutName = "default.vm";
    public static final String DEFAULT_LAYOUT_URL = "layout/default.vm";
    public static final String DEFAULT_LAYOUT_KEY = "layout";
    public static final String DEFAULT_SCREEN_CONTENT_KEY = "screen_content";
    private static final ThreadLocal screenLocal = new  ThreadLocal() {
    	 protected CharArrayWriterWrapper initialValue()
    	    {
    	        return new CharArrayWriterWrapper();
    	    }

//    	    protected volatile Object initialValue()
//    	    {
//    	        return initialValue();
//    	    }
    }

;
    private String layoutUrl;
    private String layoutKey;
    private String screenContentKey;
    private String layoutPrefix;
    private char layoutPrefixChars[];
    private String defaultLayoutName;
    private char defaultLayoutNameChars[];
    private char screenPrefixChars[];
    protected Map layoutTemplateCache;

}