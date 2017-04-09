package com.ljdc.action;

import com.ljdc.pojo.Admin;
import com.ljdc.utils.SessionsUtil;
import com.opensymphony.xwork2.Action;
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
public class AdminLoginAction extends ActionSupport {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String execute() throws Exception {
        System.out.println(username + "-----------" + password);
        // TODO: 2017/4/8 admin账户验证
//        HttpServletRequest request = ServletActionContext.getRequest();
//        HttpServletResponse response = ServletActionContext.getResponse();
//        request.getRequestDispatcher("index.html").forward(request,response);
//        response.sendRedirect("index.html");
        Session session = SessionsUtil.newSession();
        Transaction ts = session.beginTransaction();

       /* Admin data = new Admin();
        data.setPassword("123");
        data.setUsername("123");
        session.save(data);
        ts.commit();*/

        Query query = session.createQuery("from Admin a where a.username=? and a.password=?").setParameter(0, username).setParameter(1, password);
        List<Admin> admin = query.list();
        if (null != admin && admin.size() != 0) {
            SessionsUtil.closeNewSession(session);
            return SUCCESS;
        }else{
            SessionsUtil.closeNewSession(session);
            return ERROR;
        }
    }
}
