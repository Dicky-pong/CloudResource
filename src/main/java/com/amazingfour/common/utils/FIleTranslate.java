package com.amazingfour.common.utils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class FIleTranslate {

    public static File MutiFileTranstoFile(HttpServletRequest request, MultipartFile multipartfile) {

        //方法1：在指定目录，生成临时文件，然后再转换
        if (!multipartfile.isEmpty()) {

            //在根目录下创建一个tempfileDir的临时文件夹
            String contextpath = request.getContextPath() + "/upload";
            File f = new File(contextpath);
            if (!f.exists()) {
                f.mkdirs();
            }

            //在tempfileDir的临时文件夹下创建一个新的和上传文件名一样的文件
            String filename = multipartfile.getOriginalFilename();
            String filepath = contextpath + "/" + filename;
            File newFile = new File(filepath);

            //将MultipartFile文件转换，即写入File新文件中，返回File文件
            CommonsMultipartFile commonsmultipartfile = (CommonsMultipartFile) multipartfile;
            try{
                commonsmultipartfile.transferTo(newFile);
            }catch (Exception e){
                e.printStackTrace();
            }

            return newFile;

        }
        return null;
    }
}
