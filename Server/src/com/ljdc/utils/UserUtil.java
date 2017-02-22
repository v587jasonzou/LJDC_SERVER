package com.ljdc.utils;

import com.ljdc.pojo.User;
import com.ljdc.pojo.UserServer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.List;


/**
 * Created with IntelliJ IDEA
 * User:邹旭
 * Date:2016/11/2 0002
 * Time:下午 12:31
 * Desc:略
 */
@SuppressWarnings("deprecation")
public class UserUtil {
    private static SessionFactory sf;
    private static Session session;

    static {
        AnnotationConfiguration configuration = new AnnotationConfiguration().configure();
        sf = configuration.buildSessionFactory();
    }

    public static Session getSession() {
        if (session != null)
            return session;
        else {
            session = sf.openSession();
            return session;
        }
    }

    public static void closeSession() {

        session.close();
        session = null;
    }

    public static boolean query(User user) {
        session = getSession();
        session.beginTransaction();
        String hql = "from User u where u.name = :name and u.password = :password";
        Query query = session.createQuery(hql).setParameter("name", user.getName()).setParameter("password", user.getPassword());
        List<User> list = query.list();
        closeSession();
        for (User s : list
                ) {
            System.out.println(s);
        }
        if (list.size() != 0)
            return true;
        return false;
    }

    public static void add(User user) {
        session = getSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        closeSession();
    }





}
