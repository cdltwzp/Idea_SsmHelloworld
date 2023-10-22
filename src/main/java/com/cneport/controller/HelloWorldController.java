package com.cneport.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zpw
 * @date 2022-05-03 18:14
 */
@Controller
public class HelloWorldController {

    @RequestMapping("/ajax")
    public @ResponseBody Map<String, Date> ajax(){
       Map<String,Date> res = new HashMap<>();
       res.put("ajax",new Date());
        return res;
    }
    @RequestMapping("/hello")
    public String sayHello(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
        return "index";
    }

    @RequestMapping("/hellojson")
    public @ResponseBody String hellojson(){
        return "hellojson";
    }

    @RequestMapping("/exception")
    public  String exception(){
        String s = null;
        System.out.println(s.toString());
        return "hellojson";
    }
    @RequestMapping("/commit")
    public String commit(HttpServletRequest request, HttpServletResponse response){
        Map<String, String[]> parameterMap = request.getParameterMap();
        return "success";
    }
    /* 王者归来 版本  这种文件存在于内存中了
    @RequestMapping("/fileUpload")
    public String fileUpload(HttpServletRequest request, HttpServletResponse response) throws Exception{
        DiskFileUpload fileUpload = new DiskFileUpload();
        List<FileItem> fileItems = fileUpload.parseRequest(request);
        FileItem fileItem = fileItems.get(2);
        System.out.println(fileItem.getName());
        //1.
        File remoteFile = new File(new String(fileItem.getName().getBytes(StandardCharsets.UTF_8)));
        File localFile = new File("D:\\applog\\test.pdf");
        localFile.createNewFile();

        InputStream inputStream = fileItem.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(localFile);
        byte[] arr = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(arr)) != -1) {
            fileOutputStream.write(arr,0,len);
        }
        inputStream.close();
        fileOutputStream.close();

        return "success";
    }*/
/*  apache commons-fileupload  v1
    @RequestMapping("/fileUpload")
    public String fileUpload(HttpServletRequest request, HttpServletResponse response) throws Exception{
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        List<FileItem> fileItems = servletFileUpload.parseRequest(request);
        for (FileItem item: fileItems) {
                if(item.isFormField()){//如果只是表单中信息,不是表单文件
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString();
                    System.out.println(fieldName + " : " + fieldValue);
                }else {
                    InputStream inputStream = item.getInputStream();
                    File localFile = new File("D:\\applog\\test.pdf");
                    FileUtils.copyInputStreamToFile(inputStream,localFile);
                }
            }

        return "success";
    }*/
   /* apache commons-fileupload  v2*/
/*   @RequestMapping("/fileUpload")
    public String fileUpload(HttpServletRequest request, HttpServletResponse response) throws Exception{
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        List<FileItem> fileItems = servletFileUpload.parseRequest(request);
        for (FileItem item: fileItems) {
            if(item.isFormField()){//如果只是表单中信息,不是表单文件
                String fieldName = item.getFieldName();
                String fieldValue = item.getString();
                System.out.println(fieldName + " : " + fieldValue);
            }else {
                Path uploadedFile = Paths.get("D:\\applog\\test.pdf");
                item.write(uploadedFile.toFile());
            }
        }

        return "success";
    }*/

    @RequestMapping("/fileUpload")
    public String fileUpload(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // Create a factory for disk-based file items
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);

        //Create a progress listener
        ProgressListener progressListener = new ProgressListener(){
            public void update(long bytesRead, long contentLength, int items) {
                System.out.println("We are currently reading item " + items);
                if (contentLength == -1) {
                    System.out.println("So far, " + bytesRead + " bytes have been read.");
                } else {
                    System.out.println("So far, " + bytesRead + " of " + contentLength
                            + " bytes have been read.");
                }
            }
        };
        servletFileUpload.setProgressListener(progressListener);

        List<FileItem> fileItems = servletFileUpload.parseRequest(request);
        for (FileItem item: fileItems) {
            if(item.isFormField()){//如果只是表单中信息,不是表单文件
                String fieldName = item.getFieldName();
                String fieldValue = item.getString();
                System.out.println(fieldName + " : " + fieldValue);
            }else {
                Path uploadedFile = Paths.get("D:\\applog\\test.pdf");
                item.write(uploadedFile.toFile());
            }
        }




        return "success";
    }
}
