package com.ljdc.action;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ljdc.bean.Message;
import com.ljdc.pojo.*;
import com.ljdc.utils.SessionsUtil;
import com.ljdc.utils.Utils;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private static Gson gson;

    static {
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss") //java.util.Date的时间格式
                .create();
    }

    Transaction ts = null;
    Query query = null;
    Message message = new Message();
    /**
     * 【param】请求参数,Action自动获取同名
     */
    private String syncJsonData;
    /**
     * 【param】
     * 客户端最最近更新时间
     */
    private String maxAnchor;
    /**
     * 【param】
     * 用户ID
     */
    private int userId;
    private HttpServletResponse response;
    private HttpServletRequest request;

    public String getSyncJsonData() {
        return syncJsonData;
    }

    public void setSyncJsonData(String syncJsonData) {
        this.syncJsonData = syncJsonData.equals("") ? "[]" : syncJsonData;
    }

    public String getMaxAnchor() {
        return maxAnchor;
    }

    /**
     * 设置参数，校验
     *
     * @param maxAnchor
     */
    public void setMaxAnchor(String maxAnchor) {
        this.maxAnchor = maxAnchor.equals("") ? "1970-01-01 08:00:00" : maxAnchor;//new Date(0) -> "1970-01-01 08:00:00"
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = Integer.valueOf(userId);
    }

    /**
     * 新增一个单词 -> 单词库
     *
     * @param syncJsonData //请求参数
     * @return
     */
    public String addOneWordToLib() {
        System.out.println("REQUEST URI: " + request.getRequestURI());
        System.out.println("Word info: " + syncJsonData);
        WordLibServer word;
        word = new Gson().fromJson(syncJsonData, WordLibServer.class);
        Session session = SessionsUtil.newSession();
        Transaction ts = session.beginTransaction();
        int newId = (int) session.save(word);
        ts.commit();
        SessionsUtil.closeNewSession(session);
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
     * @param syncJsonData
     * @return 返回字符串到浏览器
     * @DESC 同步LearnLib1接口
     */
    public String syncLearnLib1() {
        System.out.println("REQUEST URI: " + request.getRequestURI());
        System.out.println("parm \"syncJsonData\" : " + syncJsonData);
        System.out.println("parm \"maxAnchor\" : " + maxAnchor);
        Session session = null;

        List<LearnLib1Server> list, datas;
        LearnLib1Server serverLearnLib1 = null;
        String hql = "from LearnLib1Server t where t.user.userId = ? and t.lib1.lib1Id = ? or t.learnLib1Id = ?";
        datas = gson.fromJson(syncJsonData, new TypeToken<List<LearnLib1Server>>() {
        }.getType());


        try {
            session = SessionsUtil.newSession();
            ts = session.beginTransaction();
            query = session.createQuery(hql);

            //step1:客户端需要同步的服务器的最新数据
            Date maxAnchorDate = Utils.getDateFormater().parse(maxAnchor);
            List<LearnLib1Server> learnLib1Servers_new = session.createQuery("from LearnLib1Server where modified > ?").setParameter(0, maxAnchorDate).list();
            System.out.println("learnLib1_new start : " + learnLib1Servers_new.size());

            //step2: TODO
            if (datas.size() > 0) {
                for (LearnLib1Server data : datas) {
                    query.setParameter(0, data.getUserId()).setParameter(1, data.getLib1Id()).setParameter(2, data.getLearnLib1Id());
                    list = query.list();
                    System.out.println("是否存在相同记录 :" + (list.size() == 0 ? "false" : "true"));
                    if (list.size() == 0 && data.getAnchor().compareTo(new Date(0)) <= 0) {  //不是多客户端同时新增一个记录的情况：直接新增记录
                        System.out.println("新增记录LearnLib1");
                        //关系映射
                        data.setUser((UserServer) session.load(UserServer.class, data.getUserId()));
                        data.setLib1((Lib1EnglishGrand4CoreServer) session.load(Lib1EnglishGrand4CoreServer.class, data.getLib1Id()));
                        //同步管理，响应值
                        Date date = new Date();
                        data.setModified(date);
                        data.setAnchor(date);
                        data.setStatusModify(9);
                        session.save(data);
                    } else if (list.size() != 0 && data.getAnchor().compareTo(list.get(0).getModified()) == 0 && data.getStatusModify() == 1) { //修改记录 TODO 删除记录
//                        data.setLearnLib1Id(serverLearnLib1.getLearnLib1Id()); 第一次保存用的是客户端传来的ID
                        System.out.println("修改记录LearnLib1");
                        //关系映射
                        data.setUser((UserServer) session.load(UserServer.class, data.getUserId()));
                        data.setLib1((Lib1EnglishGrand4CoreServer) session.load(Lib1EnglishGrand4CoreServer.class, data.getLib1Id()));
                        //同步管理，响应值
                        Date date = new Date();
                        data.setModified(date);
                        data.setAnchor(date);
                        data.setStatusModify(9);
                        if (data.getLearnLib1Id().equals(list.get(0).getLearnLib1Id())) {

                            session.evict(list.get(0));//更新操作，之前查找到一个相同ID的记录，必须取消与Session的关联，否则跑出异常
                        }
                        session.update(data);
                    } else if (list.size() != 0 && data.getAnchor().compareTo(list.get(0).getModified()) < 0) {//客户端需要先进行同步（强行同步客户端）
                        System.out.println("删除客户端记录LearnLib1");
                        serverLearnLib1 = list.get(0);//服务器端记录
                        //记录客户端需要删除的不同步记录
                        if (!serverLearnLib1.getLearnLib1Id().equals(data.getLearnLib1Id())) {
                            serverLearnLib1.setOldId(data.getLearnLib1Id());
                        }
                        //ORM映射关系 对象->属性
                        serverLearnLib1.setUserId(serverLearnLib1.getUser().getUserId());
                        serverLearnLib1.setLib1Id(serverLearnLib1.getLib1().getLib1Id());
                        //同步管理，响应值
                        serverLearnLib1.setAnchor(serverLearnLib1.getModified());
                        serverLearnLib1.setStatusModify(9);

                        datas.remove(data);//移除
                        if (learnLib1Servers_new.contains(serverLearnLib1)) {
                            learnLib1Servers_new.remove(serverLearnLib1);
                        }
                        datas.add(serverLearnLib1);
                    }
                }
                ts.commit();
            }

            //将只存在于服务器的数据添加到返回信息中
            System.out.println("learnLib1_new end : " + learnLib1Servers_new.size());
            if (learnLib1Servers_new.size() != 0) {
                for (LearnLib1Server data : learnLib1Servers_new) {
                    System.out.println("data.toString() :" + data.toString());
                    data.setAnchor(data.getModified());
                    data.setStatusModify(9);
                    data.setUserId(data.getUser().getUserId());
                    data.setLib1Id(data.getLib1().getLib1Id());
                    datas.add(data);
                }
            }

            for (LearnLib1Server data : datas) {//在使用GSON之前，去掉与关系属性的关系，设置为NULL
                data.setUser(null);
                data.setLib1(null);
            }

            message.setCode(201);//更新过程中遇到数据版本冲突，客户端数据需要强制更新
            message.setMsg(gson.toJson(datas));
            Utils.printToBrowser(response, message.toString());
            System.out.println("message learnLib1 : " + message.getMsg());

        } catch (HibernateException e) {
            e.printStackTrace();
            message.setCode(400);
            message.setMsg("服务器貌似遇到一点点小麻烦，请稍后重试");
            Utils.printToBrowser(response, message.toString());
            System.out.println("message learnLib1 : " + message.getMsg());
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            SessionsUtil.closeNewSession(session);
        }
        return null;
    }

    public String syncLearnLib2() {
        System.out.println("REQUEST URI: " + request.getRequestURI());
        System.out.println("parm \"syncJsonData\" : " + syncJsonData);
        System.out.println("parm \"maxAnchor\" : " + maxAnchor);
        Session session = null;


        List<LearnLib2Server> list, datas;
        LearnLib2Server serverLearnLib2 = null;
        String hql = "from LearnLib2Server t where t.user.userId = ? and t.lib2.lib2Id = ? or t.learnLib2Id = ?";
        datas = gson.fromJson(syncJsonData, new TypeToken<List<LearnLib2Server>>() {
        }.getType());

        try {
            session = SessionsUtil.newSession();
            ts = session.beginTransaction();
            query = session.createQuery(hql);

            //step1:客户端需要同步的服务器的最新数据
            Date maxAnchorDate = Utils.getDateFormater().parse(maxAnchor);
            List<LearnLib2Server> learnLib2Servers_new = session.createQuery("from LearnLib2Server where modified > ?").setParameter(0, maxAnchorDate).list();
            System.out.println("learnLib2_new start : " + learnLib2Servers_new.size());

            //step2: TODO
            if (datas.size() > 0) {
                for (LearnLib2Server data : datas) {
                    query.setParameter(0, data.getUserId()).setParameter(1, data.getLib2Id()).setParameter(2, data.getLearnLib2Id());
                    list = query.list();
                    System.out.println("是否存在相同记录 :" + (list.size() == 0 ? "false" : "true"));
                    if (list.size() == 0 && data.getAnchor().compareTo(new Date(0)) <= 0) {  //不是多客户端同时新增一个记录的情况：直接新增记录
                        System.out.println("新增记录LearnLib2");
                        //关系映射
                        data.setUser((UserServer) session.load(UserServer.class, data.getUserId()));
                        data.setLib2((Lib2MiddleSchoolServer) session.load(Lib2MiddleSchoolServer.class, data.getLib2Id()));
                        //同步管理，响应值
                        Date date = new Date();
                        data.setModified(date);
                        data.setAnchor(date);
                        data.setStatusModify(9);
                        session.save(data);
                    } else if (list.size() != 0 && data.getAnchor().compareTo(list.get(0).getModified()) == 0 && data.getStatusModify() == 1) { //修改记录 TODO 删除记录
//                        data.setLearnLib1Id(serverLearnLib1.getLearnLib1Id()); 第一次保存用的是客户端传来的ID
                        System.out.println("修改记录LearnLib2");
                        //关系映射
                        data.setUser((UserServer) session.load(UserServer.class, data.getUserId()));
                        data.setLib2((Lib2MiddleSchoolServer) session.load(Lib2MiddleSchoolServer.class, data.getLib2Id()));
                        //同步管理，响应值
                        Date date = new Date();
                        data.setModified(date);
                        data.setAnchor(date);
                        data.setStatusModify(9);
                        if (data.getLearnLib2Id().equals(list.get(0).getLearnLib2Id())) {

                            session.evict(list.get(0));//更新操作，之前查找到一个相同ID的记录，必须取消与Session的关联，否则跑出异常
                        }
                        session.update(data);
                    } else if (list.size() != 0 && data.getAnchor().compareTo(list.get(0).getModified()) < 0) {//客户端需要先进行同步（强行同步客户端）
                        System.out.println("删除客户端记录LearnLib2");
                        serverLearnLib2 = list.get(0);//服务器端记录
                        //记录客户端需要删除的不同步记录
                        if (!serverLearnLib2.getLearnLib2Id().equals(data.getLearnLib2Id())) {

                            serverLearnLib2.setOldId(data.getLearnLib2Id());
                        }
                        //ORM映射关系 对象->属性
                        serverLearnLib2.setUserId(serverLearnLib2.getUser().getUserId());
                        serverLearnLib2.setLib2Id(serverLearnLib2.getLib2().getLib2Id());
                        //同步管理，响应值
                        serverLearnLib2.setAnchor(serverLearnLib2.getModified());
                        serverLearnLib2.setStatusModify(9);
                        datas.remove(data);//移除
                        if (learnLib2Servers_new.contains(serverLearnLib2)) {
                            learnLib2Servers_new.remove(serverLearnLib2);
                        }
                        datas.add(serverLearnLib2);
                    }
                }
                ts.commit();
            }

            //将只存在于服务器的数据添加到返回信息中
            System.out.println("learnLib2_new end : " + learnLib2Servers_new.size());
            if (learnLib2Servers_new.size() != 0) {
                for (LearnLib2Server data : learnLib2Servers_new) {
                    System.out.println("data.toString() :" + data.toString());
                    data.setAnchor(data.getModified());
                    data.setStatusModify(9);
                    data.setUserId(data.getUser().getUserId());
                    data.setLib2Id(data.getLib2().getLib2Id());
                    datas.add(data);
                }
            }
            for (LearnLib2Server data : datas) {//在使用GSON之前，去掉与关系属性的关系，设置为NULL
                data.setUser(null);
                data.setLib2(null);
            }

            message.setCode(202);//更新过程中遇到数据版本冲突，客户端数据需要强制更新
            message.setMsg(gson.toJson(datas));
            Utils.printToBrowser(response, message.toString());
            System.out.println("message learnLib2 : " + message.getMsg());

        } catch (HibernateException e) {
            e.printStackTrace();
            message.setCode(400);
            message.setMsg("服务器貌似遇到一点点小麻烦，请稍后重试");
            Utils.printToBrowser(response, message.toString());
            System.out.println("message learnLib2 : " + message.getMsg());
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            SessionsUtil.closeNewSession(session);
        }

        return null;
    }

    public String syncWordDevelop() {
        System.out.println("REQUEST URI: " + request.getRequestURI());
        System.out.println("parm \"syncJsonData\" : " + syncJsonData);
        System.out.println("parm \"maxAnchor\" : " + maxAnchor);
        Session session = null;

        List<WordDevelopmentServer> list, datas;
        WordDevelopmentServer serverWordDev = null;
        String hql = "from WordDevelopmentServer t where t.graspLevel = ? and t.wordIncreaseDate = ? and t.user.userId = ? or t.wordDevId = ?";
        datas = gson.fromJson(syncJsonData, new TypeToken<List<WordDevelopmentServer>>() {
        }.getType());
        try {
            session = SessionsUtil.newSession();
            ts = session.beginTransaction();
            query = session.createQuery(hql);
            System.out.println("new Date(0) :" + new Date(0));

            //step1:客户端需要同步的服务器的最新数据
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟
            Date maxAnchorDate = sdf.parse(maxAnchor);
            List<WordDevelopmentServer> wordDevelopmentServers_new = session.createQuery("from WordDevelopmentServer where modified > ? and user.userId = ?").setParameter(0, maxAnchorDate).setParameter(1, userId).list();
            System.out.println("wordDev_new start : " + wordDevelopmentServers_new.size());
            //step2:
            if (datas.size() > 0) {
                for (WordDevelopmentServer data : datas) {
                    query.setParameter(0, data.getGraspLevel()).setParameter(1, data.getWordIncreaseDate()).setParameter(2, data.getUserId()).setParameter(3, data.getWordDevId());
                    list = query.list();
                    System.out.println("是否存在相同记录 :" + (list.size() == 0 ? "false" : "true"));
                    if (list.size() == 0 && data.getAnchor().compareTo(new Date(0)) <= 0) {  //不是多客户端同时新增一个记录的情况：直接新增记录
                        System.out.println("新增记录WordDev");
                        //关系映射
                        data.setUser((UserServer) session.load(UserServer.class, data.getUserId()));
                        //同步管理，响应值
                        Date date = new Date();
                        data.setModified(date);
                        data.setAnchor(date);
                        data.setStatusModify(9);
                        session.save(data);
                    } else if (list.size() != 0 && data.getAnchor().compareTo(list.get(0).getModified()) == 0 && data.getStatusModify() == 1) { //修改记录
//                        data.setLearnLib1Id(serverLearnLib1.getLearnLib1Id()); 第一次保存用的是客户端传来的ID
                        System.out.println("修改记录WordDev");
                        //关系映射
                        data.setUser((UserServer) session.load(UserServer.class, data.getUserId()));
                        //同步管理，响应值
                        Date date = new Date();
                        data.setModified(date);
                        data.setAnchor(date);
                        data.setStatusModify(9);
                        session.evict(list.get(0));//更新操作，之前查找到一个相同ID的记录，必须取消与Session的关联，否则跑出异常
                        session.update(data);
                    } else if (list.size() != 0 && data.getAnchor().compareTo(list.get(0).getModified()) < 0) {//客户端需要先进行同步（强行同步客户端）
                        System.out.println("删除客户端记录WordDev");
                        serverWordDev = list.get(0);//服务器端记录
                        //记录客户端需要删除的不同步记录
                        if (!data.getWordDevId().equals(serverWordDev.getWordDevId()))
                            serverWordDev.setOldId(data.getWordDevId() + "");
                        //ORM映射关系 对象->属性
                        serverWordDev.setUserId(serverWordDev.getUser().getUserId());
                        //同步管理，响应值
                        serverWordDev.setAnchor(serverWordDev.getModified());
                        serverWordDev.setStatusModify(9);

                        datas.remove(data);//移除
                        if (wordDevelopmentServers_new.contains(serverWordDev))
                            wordDevelopmentServers_new.remove(serverWordDev);//去掉客户端存在的情况，只保留服务器端单独存在的数据
                        datas.add(serverWordDev);
                    }
                }
                ts.commit();
            }

            //将只存在于服务器的数据添加到返回信息中
            System.out.println("wordDev_new end : " + wordDevelopmentServers_new.size());
            if (wordDevelopmentServers_new.size() != 0) {
                for (WordDevelopmentServer data : wordDevelopmentServers_new) {
                    System.out.println("data.toString() :" + data.toString());
                    data.setAnchor(data.getModified());
                    data.setStatusModify(9);
                    UserServer user = data.getUser();
                    data.setUserId(user.getUserId());
                    datas.add(data);
                }
            }
            for (WordDevelopmentServer data : datas) {//在使用GSON之前，去掉与关系属性的关系，设置为NULL
                data.setUser(null);
            }
            message.setCode(203);//更新过程中遇到数据版本冲突，客户端数据需要强制更新
            message.setMsg(gson.toJson(datas));
            Utils.printToBrowser(response, message.toString());
            System.out.println("message WordDevelopmentServer: " + message.getMsg());

        } catch (HibernateException e) {
            e.printStackTrace();
            message.setCode(400);
            message.setMsg("服务器貌似遇到一点点小麻烦，请稍后重试");
            Utils.printToBrowser(response, message.toString());
            System.out.println("message WordDevelopmentServer: " + message.getMsg());
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            SessionsUtil.closeNewSession(session);
        }

        return null;
    }

    public String syncStudyPlan() {
        System.out.println("REQUEST URI: " + request.getRequestURI());
        System.out.println("parm \"syncJsonData\" : " + syncJsonData);
        System.out.println("parm \"maxAnchor\" : " + maxAnchor);
        Session session = null;

        List<StudyPlan> list, datas;
        StudyPlan serverStudyPlan = null;
        String hql = "from StudyPlan t where t.user.userId = ?";
        datas = gson.fromJson(syncJsonData, new TypeToken<List<StudyPlan>>() {
        }.getType());
        try {
            session = SessionsUtil.newSession();
            ts = session.beginTransaction();
            query = session.createQuery(hql);

            //step1:客户端需要同步的服务器的最新数据
            Date maxAnchorDate = Utils.getDateFormater().parse(maxAnchor);
            List<StudyPlan> studyPlen_new = session.createQuery("from StudyPlan where modified > ? and user.userId = ?").setParameter(0, maxAnchorDate).setParameter(1, userId).list();
            System.out.println("studyPlan_new start : " + studyPlen_new.size());
            //step2:
            if (datas.size() > 0) {
                for (StudyPlan data : datas) {
                    query.setParameter(0, data.getUserId());
                    list = query.list();
                    System.out.println("是否存在相同记录 :" + (list.size() == 0 ? "false" : "true"));
                    if (list.size() == 0 && data.getAnchor().compareTo(new Date(0)) <= 0) {  //不是多客户端同时新增一个记录的情况：直接新增记录
                        System.out.println("新增记录StudyPlan");
                        //关系映射
                        data.setUser((UserServer) session.load(UserServer.class, data.getUserId()));
                        //同步管理，响应值
                        Date date = new Date();
                        data.setModified(date);
                        data.setAnchor(date);
                        data.setStatus(9);
                        session.save(data);
                    } else if (list.size() != 0 && data.getAnchor().compareTo(list.get(0).getModified()) == 0 && data.getStatus() == 1) { //修改记录
                        System.out.println("修改记录StudyPlan");
                        //关系映射
                        data.setUser((UserServer) session.load(UserServer.class, data.getUserId()));
                        //同步管理，响应值
                        Date date = new Date();
                        data.setModified(date);
                        data.setAnchor(date);
                        data.setStatus(9);
                        session.evict(list.get(0));//更新操作，之前可能查找到一个相同ID的记录，必须取消与Session的关联，否则跑出异常
                        session.update(data);
                    } else if (list.size() != 0 && data.getAnchor().compareTo(list.get(0).getModified()) < 0) {//客户端需要先进行同步（强行同步客户端）
                        System.out.println("删除客户端记录StudyPlan");
                        serverStudyPlan = list.get(0);//服务器端记录
                        //记录客户端需要删除的不同步记录
                        if (!data.getPlanId().equals(serverStudyPlan.getPlanId()))
                            serverStudyPlan.setOldId(data.getPlanId());
                        //ORM映射关系 对象->属性
                        serverStudyPlan.setUserId(serverStudyPlan.getUser().getUserId());
                        //同步管理，响应值
                        serverStudyPlan.setAnchor(serverStudyPlan.getModified());
                        serverStudyPlan.setStatus(9);

                        datas.remove(data);//移除
                        if (studyPlen_new.contains(serverStudyPlan))
                            studyPlen_new.remove(serverStudyPlan);//去掉客户端存在的情况，只保留服务器端单独存在的数据
                        datas.add(serverStudyPlan);
                    }
                }
                ts.commit();
            }

            //将只存在于服务器的数据添加到返回信息中
            System.out.println("studyPlan_new end : " + studyPlen_new.size());
            if (studyPlen_new.size() != 0) {
                for (StudyPlan data : studyPlen_new) {
                    System.out.println("data.toString() :" + data.toString());
                    data.setAnchor(data.getModified());
                    data.setStatus(9);
                    UserServer user = data.getUser();
                    data.setUserId(user.getUserId());
                    datas.add(data);
                }
            }
            for (StudyPlan data : datas) {//在使用GSON之前，去掉与关系属性的关系，设置为NULL
                data.setUser(null);
            }
            message.setCode(204);//更新过程中遇到数据版本冲突，客户端数据需要强制更新
            message.setMsg(gson.toJson(datas));
            Utils.printToBrowser(response, message.toString());
            System.out.println("message StudyPlan: " + message.getMsg());

        } catch (HibernateException e) {
            e.printStackTrace();
            message.setCode(400);
            message.setMsg("服务器貌似遇到一点点小麻烦，请稍后重试");
            Utils.printToBrowser(response, message.toString());
            System.out.println("message StudyPlan: " + message.getMsg());
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            SessionsUtil.closeNewSession(session);
        }

        return null;
    }

    public String syncLibs() {
        System.out.println("REQUEST URI: " + request.getRequestURI());
        System.out.println("parm \"syncJsonData\" : " + syncJsonData);
        System.out.println("parm \"maxAnchor\" : " + maxAnchor);
        Session session = null;

        List<Libs> list, datas = null;//客户端传来的JSON数据应该为""
        try {
            session = SessionsUtil.newSession();
            ts = session.beginTransaction();

            //step1:客户端需要同步的服务器的最新数据
            Date maxAnchorDate = Utils.getDateFormater().parse(maxAnchor);
            List<Libs> libs_new = session.createQuery("from Libs where modified > ?").setParameter(0, maxAnchorDate).list();
            //将只存在于服务器的数据添加到返回信息中
            System.out.println("libs_new end : " + libs_new.size());
            if (libs_new.size() != 0) {
                datas = new ArrayList<>();
                for (Libs data : libs_new) {
                    System.out.println("data.toString() :" + data.toString());
                    data.setAnchor(data.getModified());
                    data.setStatus(9);
                    datas.add(data);
                }
            }
            message.setCode(205);//更新过程中遇到数据版本冲突，客户端数据需要强制更新
            message.setMsg(gson.toJson(datas));
            Utils.printToBrowser(response, message.toString());
            System.out.println("message Libs: " + message.getMsg());

        } catch (HibernateException e) {
            e.printStackTrace();
            message.setCode(400);
            message.setMsg("服务器貌似遇到一点点小麻烦，请稍后重试");
            Utils.printToBrowser(response, message.toString());
            System.out.println("message Libs: " + message.getMsg());
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            SessionsUtil.closeNewSession(session);
        }

        return null;
    }

    public String syncUsers() {
        System.out.println("REQUEST URI: " + request.getRequestURI());
        System.out.println("parm \"syncJsonData\" : " + syncJsonData);
        System.out.println("parm \"maxAnchor\" : " + maxAnchor);
        Session session = null;

        List<UserServer> list; //服务器端查询结果
        List<UserServer> datas;//客户端更新数据
        String hql = "from UserServer t where t.userId = ?";
        datas = gson.fromJson(syncJsonData, new TypeToken<List<UserServer>>() {
        }.getType());
        try {
            session = SessionsUtil.newSession();
            ts = session.beginTransaction();
            query = session.createQuery(hql);
            //同步操作:
            if (datas.size() > 0) {
                for (UserServer data : datas) {
                    query.setParameter(0, data.getUserId());
                    list = query.list();
                    System.out.println("是否存在相同记录 :" + (list.size() == 0 ? "false" : "true"));
                    if (list.size() != 0 && data.getAnchor().compareTo(list.get(0).getModified()) == 0 && data.getStatus() == 1) { //Client修改记录
                        System.out.println("修改记录StudyPlan");
                        //同步管理，响应值
                        Date date = new Date();
                        data.setModified(date);
                        data.setAnchor(date);
                        data.setStatus(9);
                        session.evict(list.get(0));//更新操作，之前可能查找到一个相同ID的记录，必须取消与Session的关联，否则跑出异常
                        session.update(data);
                    } else if (list.size() != 0 && data.getAnchor().compareTo(list.get(0).getModified()) < 0) {//客户端2先进行了更新，客户端1需要先同步客户端2的更新
                        UserServer user = list.get(0);//服务器端记录
                        //同步管理，响应值
                        user.setAnchor(user.getModified());
                        user.setStatus(9);
                        datas.remove(data);//移除
                        datas.add(user);
                    }
                }
                ts.commit();
            }
            for (UserServer data : datas) {//在使用GSON之前，去掉与关系属性的关系，设置为NULL
                data.setStudyPlen(null);
                data.setWordDevelopment(null);
                data.setLearnLib1(null);
                data.setLearnLib2(null);
            }
            message.setCode(206);//更新过程中遇到数据版本冲突，客户端数据需要强制更新
            message.setMsg(gson.toJson(datas));
            Utils.printToBrowser(response, message.toString());
            System.out.println("message users:json " + message.getMsg());

        } catch (HibernateException e) {
            e.printStackTrace();
            message.setCode(400);
            message.setMsg("服务器貌似遇到一点点小麻烦，请稍后重试");
            Utils.printToBrowser(response, message.toString());
            System.out.println("message user:json -> " + message.getMsg());
        } finally {
            SessionsUtil.closeNewSession(session);
        }

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
