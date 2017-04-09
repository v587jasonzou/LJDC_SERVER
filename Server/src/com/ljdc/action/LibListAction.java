package com.ljdc.action;

import com.google.gson.Gson;
import com.ljdc.bean.Message;
import com.ljdc.pojo.Admin;
import com.ljdc.pojo.Lib;
import com.ljdc.pojo.Libs;
import com.ljdc.pojo.RemovedLib;
import com.ljdc.utils.SessionsUtil;
import com.ljdc.utils.Utils;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User:邹旭
 * Date:2017/4/7
 * Time:23:46
 * Desc:略
 */
public class LibListAction extends ActionSupport {

    public String libList() throws Exception {
        // TODO: 2017/4/8 admin账户验证
//        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
//        request.getRequestDispatcher("index.html").forward(request,response);
//        response.sendRedirect("index.html");
        Session session = SessionsUtil.newSession();
        Transaction ts = session.beginTransaction();

        Query query = session.createQuery("from Libs");
        List<Libs> list = query.list();
        Gson gson = Utils.getGsonForDate();
        String data = gson.toJson(list);
        if (list != null && list.size() != 0) {
            Utils.printToBrowser(response, gson.toJson(list));
        } else
            Utils.printToBrowser(response, "");

        SessionsUtil.closeNewSession(session);
        return null;
    }

    //请求词库列表页面
    public String libs() throws Exception {
        System.out.println(">>>>>>>>>libs");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
//        request.getRequestDispatcher("index.html").forward(request,response);
//        response.sendRedirect("index.html");
        Session session = SessionsUtil.newSession();
        Transaction ts = session.beginTransaction();

        Query query = session.createQuery("from Libs");
        List<Libs> list = query.list();
        request.setAttribute("libs", list);

        SessionsUtil.closeNewSession(session);
        return "success";
    }

    public String dellib() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        int id = Integer.parseInt(request.getParameter("id"));//数据库:libsId
        Session session = SessionsUtil.newSession();
        Transaction ts = session.beginTransaction();

        Libs lib = (Libs) session.get(Libs.class, id);
        String libName = lib.getLibName();
        session.delete(lib);

        RemovedLib removedLib = new RemovedLib();
        removedLib.setLibName(libName);
        session.save(removedLib);

        Query libQ = session.createQuery("from Lib where libName=?");
        libQ.setParameter(0, libName);
        List<Lib> libNameList = libQ.list();
        for (Lib data : libNameList) {
            session.delete(data);
        }

        ts.commit();
        SessionsUtil.closeNewSession(session);
        return null;
    }
}
