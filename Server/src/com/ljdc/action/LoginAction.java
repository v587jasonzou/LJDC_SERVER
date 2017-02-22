package com.ljdc.action;

import com.ljdc.pojo.UserServer;
import com.ljdc.utils.SessionsUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.xml.ws.Action;
import java.util.List;


/**
 * Created with IntelliJ IDEA
 * User:邹旭
 * Date:2016/11/9 0009
 * Time:下午 6:04
 * Desc:略
 */
public class LoginAction extends ActionSupport implements ModelDriven<UserServer> {
    private UserServer user = new UserServer();

    public String register() {
        Session session = SessionsUtil.getSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        SessionsUtil.closeSession();
        return SUCCESS;
    }

    public String login() {
        Session session = SessionsUtil.getSession();
        session.beginTransaction();

        String hqlByPhone = "from UserServer u WHERE u.password = :password and u.phone = :phone";
        Query query = session.createQuery(hqlByPhone).setParameter("password", user.getPassword()).setParameter("phone", user.getPhone());
        List<UserServer> list = query.list();
        for (UserServer u : list
                ) {
            System.out.println(u.toString());
        }

        session.getTransaction().commit();
        SessionsUtil.closeSession();
        return SUCCESS;
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
}
