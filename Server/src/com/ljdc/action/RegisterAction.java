package com.ljdc.action;

import com.ljdc.pojo.UserServer;
import com.ljdc.utils.UserUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.hibernate.Session;

/**
 * Created with IntelliJ IDEA
 * User:邹旭
 * Date:2017/2/22 0022
 * Time:上午 1:51
 * Desc:注册接口
 */
public class RegisterAction extends ActionSupport implements ModelDriven<UserServer> {
    private UserServer user = new UserServer();

    @Override
    public String execute() throws Exception {
//        return super.execute();
        Session session = UserUtil.getSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        UserUtil.closeSession();
        return  SUCCESS;
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
