package com.ljdc.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * User:邹旭
 * Date:2017/2/22 0022
 * Time:下午 7:57
 * Desc:略
 */
@Entity
@Table(name = "learn_lib2", schema = "server_ljdc", catalog = "")
public class LearnLib2Server {
    private String learnLib2Id;
    private int graspLevel;
    private Date updataTime;
    private Date modified;
    private Lib2MiddleSchoolServer lib2;
    private UserServer user;

    private int statusModify;//接收客户端数据
    private Date anchor;//接收客户端数据
    private int userId; //用户ID
    private int lib2Id; //词库1ID
    private String oldId;//需要删除的不同步的记录ID

    @Transient
    public String getOldId() {
        return oldId;
    }

    public void setOldId(String oldId) {
        this.oldId = oldId;
    }

    @Transient
    public int getStatusModify() {
        return statusModify;
    }

    public void setStatusModify(int statusModify) {
        this.statusModify = statusModify;
    }

    @Transient
    public Date getAnchor() {
        return anchor;
    }

    public void setAnchor(Date anchor) {
        this.anchor = anchor;
    }

    @Transient
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Transient
    public int getLib2Id() {
        return lib2Id;
    }

    public void setLib2Id(int lib2Id) {
        this.lib2Id = lib2Id;
    }


    @Id
    @GeneratedValue(generator = "ASSIGN")
    @GenericGenerator(name = "ASSIGN", strategy = "assigned")
    @Column(name = "learnLib2Id", nullable = false)
    public String getLearnLib2Id() {
        return learnLib2Id;
    }

    public void setLearnLib2Id(String learnLib2Id) {
        this.learnLib2Id = learnLib2Id;
    }

    @Basic
    @Column(name = "graspLevel", nullable = false)
    public int getGraspLevel() {
        return graspLevel;
    }

    public void setGraspLevel(int graspLevel) {
        this.graspLevel = graspLevel;
    }

    @Basic
    @Column(name = "updataTime", nullable = false)
    public Date getUpdataTime() {
        return updataTime;
    }

    public void setUpdataTime(Date updataTime) {
        this.updataTime = updataTime;
    }

    @Basic
    @Column(name = "modified", nullable = false)
    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LearnLib2Server that = (LearnLib2Server) o;

        if (learnLib2Id != that.learnLib2Id) return false;
        if (graspLevel != that.graspLevel) return false;
        if (updataTime != null ? !updataTime.equals(that.updataTime) : that.updataTime != null) return false;
        if (modified != null ? !modified.equals(that.modified) : that.modified != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = learnLib2Id.hashCode();
        result = 31 * result + graspLevel;
        result = 31 * result + (updataTime != null ? updataTime.hashCode() : 0);
        result = 31 * result + (modified != null ? modified.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lib2Id", referencedColumnName = "lib2Id")
    public Lib2MiddleSchoolServer getLib2() {
        return lib2;
    }

    public void setLib2(Lib2MiddleSchoolServer lib2) {
        this.lib2 = lib2;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    public UserServer getUser() {
        return user;
    }

    public void setUser(UserServer user) {
        this.user = user;
    }
}
