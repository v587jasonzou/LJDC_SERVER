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

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟

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
    public void queryUser() {
        String hql = "from UserServer";
        Query query = session.createQuery(hql);
        List<UserServer> list = query.list();
        for (UserServer s : list
                ) {
            System.out.println(s);

        }
    }

    @Test
    public void queryWordDev() throws ParseException {
        String hql = "from WordDevelopmentServer t where t.graspLevel = ? and t.wordIncreaseDate = ? and t.wordsIncreaseNum = ?";
        String hql1 = "from WordDevelopmentServer ";
        Query query = session.createQuery(hql);
        query.setParameter(0,1).setParameter(1,sdf.parse("2017-03-02 13:11:26")).setParameter(2,1);
        List<WordDevelopmentServer> list = query.list();
        for (WordDevelopmentServer s : list) {
            System.out.println("wordDev :"+s.toString());
            System.out.println("user :"+s.getUser().toString());
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
