package com.ljdc.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Collection;
import java.util.List;

//文件上传:maxsize(2Mb)
public class FileUploadAction extends ActionSupport {
    private String username;

    //　　这里用List来存放上传过来的文件，file同样指的是临时文件夹中的临时文件，而不是真正上传过来的文件
    private List<File> file;

    //　　这个List存放的是文件的名字，和List<File>中的文件相对应
    private List<String> fileFileName;

    private List<String> fileContentType;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<File> getFile() {
        return file;
    }

    public void setFile(List<File> file) {
        this.file = file;
    }

    public List<String> getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(List<String> fileFileName) {
        this.fileFileName = fileFileName;
    }

    public List<String> getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(List<String> fileContentType) {
        this.fileContentType = fileContentType;
    }

    @Override
    public Collection<String> getActionErrors() {
        return super.getActionErrors();
    }

    @Override
    public String execute() throws Exception {

        String root = ServletActionContext.getServletContext().getRealPath("/upload");

        for (int i = 0; i < file.size(); i++) {
            InputStream is = new FileInputStream(file.get(i));

            File dir = new File(root);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            OutputStream os = new FileOutputStream(new File(root, fileFileName.get(i)));

            byte[] buffer = new byte[500];

            @SuppressWarnings("unused")
            int length = 0;

            while (-1 != (length = is.read(buffer, 0, buffer.length))) {
                os.write(buffer);
            }

            os.close();
            is.close();
        }
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        response.getWriter().print("文件上传成功!");

        return null;
    }
}