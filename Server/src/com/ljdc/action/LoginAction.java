package com.ljdc.action;

import com.ljdc.bean.Message;
import com.ljdc.pojo.UserServer;
import com.ljdc.utils.SessionsUtil;
import com.ljdc.utils.Utils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * Created with IntelliJ IDEA
 * User:邹旭
 * Date:2016/11/9 0009
 * Time:下午 6:04
 * Desc:处理登录与注册请求（Client）
 */
@SuppressWarnings("ALL")
public class LoginAction extends ActionSupport implements ModelDriven<UserServer>, ServletResponseAware, ServletRequestAware {
    Message message = new Message();
    private UserServer user = new UserServer();
    private HttpServletResponse response;
    private HttpServletRequest request;

    /**
     * 注册接口
     *
     * @return
     */
    public String register() {
        System.out.println("REQUEST URI: "+request.getRequestURI());
        Session session = SessionsUtil.getSession();
        Transaction ts = session.beginTransaction();
        int newId = (int) session.save(user);
        ts.commit();
        SessionsUtil.closeSession();
        if (newId > 0) {
            message.setCode(200);
            message.setMsg("注册成功");
        } else {
            message.setCode(400);
            message.setMsg("注册失败");
        }
        Utils.printToBrowser(response, message.toString());
        return null;
    }


    /**
     * 登录接口
     * 返回字符串到浏览器
     *
     * @return
     */
    public String login() {
        System.out.println("REQUEST URI: "+request.getRequestURI());
        Session session = SessionsUtil.getSession();
        Transaction ts = session.beginTransaction();

        String hql = "from UserServer u where u.password = :parm1 and (u.phone = :parm2 or u.nickname = :parm3 or u.email = :parm4)";
        Query query = session.createQuery(hql).setParameter("parm1", user.getPassword()).setParameter("parm2", user.getPhone()).setParameter("parm3", user.getNickname()).setParameter("parm4", user.getEmail());
        List<UserServer> list = query.list();
        if (list.size() == 0) {
            //TODO 登录失败
            message.setCode(400);
            message.setMsg("找不到该用户");
            Utils.printToBrowser(response, message.toString());
        } else {
            //TODO 登录成功
            message.setCode(200);
            message.setMsg("登录成功");
            Utils.printToBrowser(response, message.toString());
        }
        ts.commit();
        SessionsUtil.closeSession();
        return null;
    }


    /**
     * Gets the model to be pushed onto the ValueStack instead of the Action itself.
     *
     * @return the model
     */
    @Override
    public UserServer getModel() {
        return user;
    }

    /**
     * Sets the HTTP response object in implementing classes.
     *
     * @param response the HTTP response.
     */
    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    /**
     * Sets the HTTP request object in implementing classes.
     *
     * @param request the HTTP request.
     */
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }
}
