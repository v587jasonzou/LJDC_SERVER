package com.ljdc.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;


/**
 * Created with IntelliJ IDEA
 * User:邹旭
 * Date:2016/11/2 0002
 * Time:下午 12:31
 * Desc:略
 */
@SuppressWarnings("deprecation")
public class SessionsUtil {
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
}
