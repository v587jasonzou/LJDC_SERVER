package com.ljdc.action;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

    private static Gson gson;

    static {
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss") //java.util.Date的时间格式
                .create();
    }

    /**
     * 注册接口
     *
     * @return
     */
    public String register() {
        System.out.println("REQUEST URI: "+request.getRequestURI());
        System.out.println("User info: "+user.toString());
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

        String hql = "from UserServer u where u.password = ? and (u.phone = ? or u.email = ?)";
        Query query = session.createQuery(hql).setParameter(0, user.getPassword()).setParameter(1, user.getPhone()).setParameter(2, user.getEmail());
        List<UserServer> list = query.list();
        if (list.size() == 0) {
            //TODO 登录失败
            message.setCode(400);
            message.setMsg("找不到该用户");
            Utils.printToBrowser(response, message.toString());
        } else {
            //TODO 登录成功
            UserServer user = list.get(0);
            message.setCode(200);
            System.out.println("---------------------------------here");
            user.setLearnLib1(null);
            user.setLearnLib2(null);
            user.setWordDevelopment(null);
            user.setStudyPlen(null);
            message.setMsg(gson.toJson(user));
            System.out.println("message user : "+message.getMsg());
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
