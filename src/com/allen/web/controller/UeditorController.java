package com.allen.web.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.allen.core.common.Const;
import com.baidu.ueditor.ActionEnter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;

/**
 * 
* @ClassName: UeditorController 
* @Description: UEditor控制器
* @author wenquan
* @date 2016年1月15日 上午9:25:39 
*
 */
@Controller
@RequestMapping(value = "/ueditor")
public class UeditorController {

        @RequestMapping("/dispatch")
        public void config(HttpServletRequest request,  HttpServletResponse response, String action) {
                response.setContentType("application/json");               
                String rootPath = request.getSession().getServletContext().getRealPath("/");

                try {
                        String exec = new ActionEnter(request, rootPath).exec();
                        PrintWriter writer = response.getWriter();
                        writer.write(exec);
                        writer.flush();
                        writer.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
                
        }
        
        @RequestMapping(value = "/upload", method = RequestMethod.GET)    
        @ResponseBody    
        public String upload(HttpServletRequest request, HttpServletResponse response) throws IOException {    
            String action = request.getParameter("action");    
            if ("config".equals(action)) {    
            	String rootPath = request.getSession().getServletContext().getRealPath("/");
            	String exec = new ActionEnter(request, rootPath).exec();
                return exec;
            }
			return null;  
        }  
          
        @RequestMapping(value = "/upload", method = RequestMethod.POST)    
        @ResponseBody    
        public String upload(HttpServletRequest request,@RequestParam CommonsMultipartFile upfile) throws IOException {    
            Map<String, String> result = Maps.newHashMap();  
            System.out.println(upfile.getFileItem().getFieldName());  
            String path = getFilePath(upfile);  
            File file = new File(path);  
            System.out.println(path);  
            //返回类型    
            String rootPath = request.getSession().getServletContext().getRealPath("/");
            result.put("url", "/ueditor/show.html?filePath=" + path);  
            result.put("size", String.valueOf(file.length()));    
            result.put("type", file.getName().substring(file.getName().lastIndexOf(".")));    
            result.put("state", Const.SUCCESS);  
            result.put("original",upfile.getOriginalFilename());
            ObjectMapper objectMapper = new ObjectMapper(); 
            return objectMapper.writeValueAsString(result);    
        }  
          
        @RequestMapping(value = "/show", method = RequestMethod.GET)  
        public void show(String filePath, HttpServletResponse response) throws IOException {  
              
            File file = getFile(filePath);  
      
            response.setDateHeader("Expires", System.currentTimeMillis() + 1000 * 60 * 60 * 24);  
            response.setHeader("Cache-Control", "max-age=60");  
            OutputStream os = response.getOutputStream();  
      
            FileInputStream is = null;  
            try {  
                is = new FileInputStream(file);  
                IOUtils.copy(is, os);  
            } catch (FileNotFoundException e) {  
                response.setStatus(404);  
                return;  
            } finally {  
                if (null != is) {  
                    is.close();  
                }  
                if (null != os) {  
                    os.flush();  
                    os.close();  
                }  
            }  
        }  
          
        protected String getFilePath(CommonsMultipartFile uploadFile){  
            String absolutePath = "D:/upload";  
            File folder = new File(absolutePath);  
            if (!folder.exists()) {  
                folder.mkdirs();  
            }  
            String rawName = uploadFile.getFileItem().getName();  
            String fileExt = rawName.substring(rawName.lastIndexOf("."));  
            String newName = System.currentTimeMillis() + UUID.randomUUID().toString() + fileExt;  
            File saveFile = new File(absolutePath + File.separator + newName);  
            try {  
                uploadFile.getFileItem().write(saveFile);  
            } catch (Exception e) {  
                e.printStackTrace();  
                return "";  
            }  
            return absolutePath + "/" + newName;  
        }  
          
        protected File getFile(String path){  
            File file = new File(path);  
            return file;  
              
        }  

}