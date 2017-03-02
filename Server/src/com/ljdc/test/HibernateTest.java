package com.ljdc.test;

import com.ljdc.pojo.UserServer;
import com.ljdc.pojo.WordDevelopmentServer;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;


/**
 * Created with IntelliJ IDEA
 * User:邹旭
 * Date:2016/11/2 0002
 * Time:下午 12:31
 * Desc:略
 */
@SuppressWarnings("deprecation")
public class HibernateTest {
    SessionFactory sf;
    Session session;

    @Before
    public void init() {
        AnnotationConfiguration configuration = new AnnotationConfiguration().configure();
        sf = configuration.buildSessionFactory();
        session = sf.openSession();
        Transaction transaction = session.beginTransaction();
    }

    @After
    public void destroy() {
        session.getTransaction().commit();
        session.close();
        sf.close();
    }

    @Test
    public void query() {
        String hql = "from UserServer ";
        Query query = session.createQuery(hql);
        List<UserServer> list = query.list();
        for (UserServer s : list
                ) {
            System.out.println(s);

        }
    }

    @Test
    public void insert() {
        UserServer userServer = new UserServer();
        userServer.setUserId(1);
        userServer.setPassword("23");
        WordDevelopmentServer wordDevelopmentServer = new WordDevelopmentServer();
        wordDevelopmentServer.setModified(new Date());
        wordDevelopmentServer.setWordIncreaseDate(new Date());
        wordDevelopmentServer.setUser(userServer);
        session.save(userServer);
        session.save(wordDevelopmentServer);
    }


}
