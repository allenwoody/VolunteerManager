package com.allen.core.feature.orm.mybatis;

import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.RowBounds;

@Intercepts({ @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class }) })
public class PaginationResultSetHandlerInterceptor implements Interceptor {

    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final DefaultReflectorFactory DEFAULT_ReflectorFactory_FACTORY = new DefaultReflectorFactory();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
    	DefaultResultSetHandler resultSetHandler = (DefaultResultSetHandler) invocation.getTarget();
        MetaObject metaStatementHandler = MetaObject.forObject(resultSetHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_ReflectorFactory_FACTORY);
        RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("rowBounds");

        Object result = invocation.proceed();

        if (rowBounds instanceof Page) {
            metaStatementHandler.setValue("rowBounds.result", result);
        }
        return result;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

}
