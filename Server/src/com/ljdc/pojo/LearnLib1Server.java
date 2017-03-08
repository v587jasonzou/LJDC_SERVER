package com.ljdc.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * User:邹旭
 * Date:2017/2/22 0022
 * Time:下午 7:57
 * Desc:略
 */
@Entity
@Table(name = "learn_lib1", schema = "server_ljdc", catalog = "")
public class LearnLib1Server {
    private String learnLib1Id;
    private int graspLevel;
    private Date updataTime;
    private Date modified;
    private Lib1EnglishGrand4CoreServer lib1;
    private UserServer user;

    private int statusModify;//接收客户端数据
    private Date anchor;//接收客户端数据
    private int userId; //用户ID
    private int lib1Id; //词库1ID
    private String oldId;//需要删除的不同步的记录ID

    @Transient
    public String getOldId() {
        return oldId;
    }

    public void setOldId(String oldId) {
        this.oldId = oldId;
    }

    @Transient
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Transient
    public int getLib1Id() {
        return lib1Id;
    }

    public void setLib1Id(int lib1Id) {
        this.lib1Id = lib1Id;
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

    @Id
    @GeneratedValue(generator = "ASSIGN")
    @GenericGenerator(name = "ASSIGN", strategy = "assigned")
    @Column(name = "learnLib1Id", nullable = false)
    public String getLearnLib1Id() {
        return learnLib1Id;
    }

    public void setLearnLib1Id(String learnLib1Id) {
        this.learnLib1Id = learnLib1Id;
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


    /**
     * 服务器修改时间
     *
     * @return
     */
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

        LearnLib1Server that = (LearnLib1Server) o;

        if (learnLib1Id != that.learnLib1Id) return false;
        if (graspLevel != that.graspLevel) return false;
        if (updataTime != null ? !updataTime.equals(that.updataTime) : that.updataTime != null) return false;
        if (modified != null ? !modified.equals(that.modified) : that.modified != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = learnLib1Id.hashCode();
        result = 31 * result + graspLevel;
        result = 31 * result + (updataTime != null ? updataTime.hashCode() : 0);
        result = 31 * result + (modified != null ? modified.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lib1Id", referencedColumnName = "lib1Id")
    public Lib1EnglishGrand4CoreServer getLib1() {
        return lib1;
    }

    public void setLib1(Lib1EnglishGrand4CoreServer lib1) {
        this.lib1 = lib1;
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
