package com.ljdc.action;

import com.ljdc.pojo.LearnLib;
import com.ljdc.pojo.Lib;
import com.ljdc.pojo.UserServer;
import com.ljdc.utils.SessionsUtil;
import com.ljdc.utils.Utils;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA
 * User:邹旭
 * Date:2017/4/9
 * Time:0:15
 * Desc:略
 */
public class Test extends ActionSupport {

    public String test() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpSession session = request.getSession();
        session.setAttribute("123", "我擦嘞");
        request.setAttribute("123", "沃日");

//        ServletActionContext.getRequest().getRequestDispatcher("/jsp/welcome.jsp");
        return SUCCESS;
    }

    public String addLearnLib() {
        Session session = SessionsUtil.newSession();
        Transaction ts = session.beginTransaction();
        LearnLib learnLib = new LearnLib();
        UUID uuid = UUID.randomUUID();
        learnLib.setLearnLibId(uuid.toString());
        learnLib.setUser((UserServer) session.load(UserServer.class, 10));
        learnLib.setLib((Lib) session.load(Lib.class,1054));
        learnLib.setModified(new Date());
        learnLib.setGraspLevel(1);
        learnLib.setUpdateTime(new Date());
        session.save(learnLib);
        ts.commit();
        SessionsUtil.closeNewSession(session);
        return null;
    }


}
