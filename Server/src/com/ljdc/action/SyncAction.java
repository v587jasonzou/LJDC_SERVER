package com.ljdc.action;

import com.google.gson.Gson;
import com.ljdc.bean.Message;
import com.ljdc.pojo.UserServer;
import com.ljdc.pojo.WordLibServer;
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
public class SyncAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {
    Message message = new Message();
    private HttpServletResponse response;
    private HttpServletRequest request;
    private String syncJsonData; //【parm】同步数据  ，接收请求参数

    public String getSyncJsonData() {
        return syncJsonData;
    }

    public void setSyncJsonData(String syncJsonData) {
        this.syncJsonData = syncJsonData;
    }


    /**
     * 新增一个单词 -> 单词库
     *
     * @return
     */
    public String addOneWordToLib() {
        System.out.println("REQUEST URI: " + request.getRequestURI());
        System.out.println("Word info: " + syncJsonData);
        WordLibServer word;
        word = new Gson().fromJson(syncJsonData, WordLibServer.class);
        Session session = SessionsUtil.getSession();
        Transaction ts = session.beginTransaction();
        int newId = (int) session.save(word);
        ts.commit();
        SessionsUtil.closeSession();
        if (newId > 0) {
            message.setCode(200);
            message.setMsg("插入成功");
        } else {
            message.setCode(400);
            message.setMsg("插入失败");
        }
        Utils.printToBrowser(response, message.toString());//返回服务器消息
        return null;
    }


    /**
     * 登录接口
     * 返回字符串到浏览器
     *
     * @return
     */
    public String update() {
     /*   System.out.println("REQUEST URI: " + request.getRequestURI());
        Session session = SessionsUtil.getSession();
        Transaction ts = session.beginTransaction();

        String hql = "from UserServer u where u.password = :parm1 and (u.phone = :parm2 or u.email = :parm4)";
        Query query = session.createQuery(hql).setParameter("parm1", user.getPassword()).setParameter("parm2", user.getPhone()).setParameter("parm4", user.getEmail());
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
            message.setMsg(new Gson().toJson(user));
            System.out.println("message user : " + message.getMsg());
            Utils.printToBrowser(response, message.toString());
        }
        ts.commit();
        SessionsUtil.closeSession();*/
        return null;
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
