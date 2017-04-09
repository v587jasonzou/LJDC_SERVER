package com.ljdc.action;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ljdc.pojo.Lib;
import com.ljdc.pojo.Libs;
import com.ljdc.pojo.WordLibServer;
import com.ljdc.utils.SessionsUtil;
import com.ljdc.utils.Utils;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

//文件上传:maxsize(2Mb)
public class LibUploadAction extends ActionSupport {

    private String libname;

    //　　这里用List来存放上传过来的文件，file同样指的是临时文件夹中的临时文件，而不是真正上传过来的文件
    private List<File> file;

    //　　这个List存放的是文件的名字，和List<File>中的文件相对应
    private List<String> fileFileName;

    private List<String> fileContentType;

    public String getLibname() {
        return libname;
    }

    public void setLibname(String libname) {
        this.libname = libname;
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

    public String libupload() throws Exception {
        System.out.println(">>>>>>>>>>>>lib文件上传");
        HttpServletRequest request = ServletActionContext.getRequest();
        Session session = SessionsUtil.newSession();
        Transaction ts = session.beginTransaction();
        Gson gson = Utils.getGsonForDate();
        Query query = session.createQuery("select count (*) from WordLibServer where word=?");
        Query query1 = session.createQuery("from WordLibServer where word=?");
        Query query2 = session.createQuery("select count (*) from Lib where libName=? and wordLib.wordId=?");
        long count = 0;
        for (int i = 0; i < file.size(); i++) {
            File f = file.get(i);
            String str = Utils.getStringFromFile(f);//获取文件内容
            System.out.println(libname + "------>>>>>" + str);
            List<WordLibServer> list = gson.fromJson(str, new TypeToken<List<WordLibServer>>() {
            }.getType());
            for (WordLibServer data : list) {
                query.setParameter(0, data.getWord());
                count = (long) query.uniqueResult();
                if (count == 0) {
                    session.save(data);
                }

                query1.setParameter(0, data.getWord());
                WordLibServer foreign = (WordLibServer) query1.list().get(0);//外键

                query2.setParameter(0, libname).setParameter(1, foreign.getWordId());
                count = (long) query2.uniqueResult();
                if (count == 0) {
                    Lib lib = new Lib();
                    lib.setWordLib(foreign);
                    lib.setLibName(libname);
                    lib.setModified(new Date());
                    session.save(lib);
                }
            }
            saveLibFile(f, libname+".json");
        }
        ts.commit();
        SessionsUtil.closeNewSession(session);

        updateLibs();
      /*  HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        response.getWriter().print("文件上传成功!");*/

        return SUCCESS;
    }

    public void saveLibFile(File file, String libName) throws IOException {
        String root = ServletActionContext.getServletContext().getRealPath("/upload");
        InputStream is = new FileInputStream(file);

        File dir = new File(root);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        OutputStream os = new FileOutputStream(new File(root, libName));

        byte[] buffer = new byte[500];

        @SuppressWarnings("unused")
        int length = 0;

        while (-1 != (length = is.read(buffer, 0, buffer.length))) {
            os.write(buffer);
        }

        os.close();
        is.close();
    }

    public void updateLibs() {
        Session session = SessionsUtil.newSession();
        Transaction ts = session.beginTransaction();

        List<Libs> libsList;
//        List<Integer> countList = new ArrayList<>();//词库的词汇数量
        List<Lib> libList;//group by 查询结果

        Query libQ = session.createQuery("from Lib group by libName");
        libList = libQ.list();

        for (int i=0;i<libList.size();i++) {

            String libName = libList.get(i).getLibName();
            Query countLibQ = session.createQuery("select count(*) from Lib where libName=?");
            countLibQ.setParameter(0, libName);
            long c = (long) countLibQ.uniqueResult();
//            countList.add(c);

            Query libsQ = session.createQuery("from Libs where libName=?");
            libsQ.setParameter(0, libName);
            List libsQL = libsQ.list();

            if (libsQL != null && libsQL.size() != 0) {
                Libs libs = (Libs) libsQL.get(0);
                libs.setTotalNum((int) c);
                libs.setModified(new Date());
                session.update(libs);
            }else{//新建
                Libs libs = new Libs();
                libs.setModified(new Date());
                libs.setTotalNum((int) c);
                libs.setLibName(libName);
                session.save(libs);
            }
        }
        ts.commit();
        SessionsUtil.closeNewSession(session);
    }
}