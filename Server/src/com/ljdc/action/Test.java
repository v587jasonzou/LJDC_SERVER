package com.ljdc.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA
 * User:邹旭
 * Date:2017/4/9
 * Time:0:15
 * Desc:略
 */
public class Test extends ActionSupport{

    public String test() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpSession session = request.getSession();
        session.setAttribute("123","我擦嘞");
        request.setAttribute("123","沃日");

//        ServletActionContext.getRequest().getRequestDispatcher("/jsp/welcome.jsp");
        return SUCCESS;
    }
}
