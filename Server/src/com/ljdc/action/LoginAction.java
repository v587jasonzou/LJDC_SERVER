package com.ljdc.action;

import com.ljdc.pojo.User;
import com.ljdc.utils.UserUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


/**
 * Created with IntelliJ IDEA
 * User:邹旭
 * Date:2016/11/9 0009
 * Time:下午 6:04
 * Desc:略
 */
public class LoginAction extends ActionSupport implements ModelDriven<User> {
    private User user = new User();

    public String register() {
        UserUtil.add(user);
        return SUCCESS;
    }

    public String login() {
        boolean res = UserUtil.query(user);
        if (res)
            return SUCCESS;
        return "register";
    }

    /**
     * Gets the model to be pushed onto the ValueStack instead of the Action itself.
     *
     * @return the model
     */
    @Override
    public User getModel() {
        return user;
    }
}
