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
@Table(name = "learn_lib", schema = "server_ljdc", catalog = "")
public class LearnLib {

    private String learnLibId;
    private int graspLevel;
    private Date updateTime;
    private Date modified;
    private Lib lib;
    private UserServer user;

    private int statusModify;//接收客户端数据
    private Date anchor;//接收客户端数据
    private int userId; //用户ID
    private int libId; //词库1ID
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
    public int getLibId() {
        return libId;
    }

    public void setLibId(int libId) {
        this.libId = libId;
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
    @Column(name = "learnLibId", nullable = false)
    public String getLearnLibId() {
        return learnLibId;
    }

    public void setLearnLibId(String learnLibId) {
        this.learnLibId = learnLibId;
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
    @Column(name = "updateTime", nullable = false)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libId", referencedColumnName = "libId")
    public Lib getLib() {
        return lib;
    }

    public void setLib(Lib lib) {
        this.lib = lib;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    public UserServer getUser() {
        return user;
    }

    public void setUser(UserServer user) {
        this.user = user;
    }
}
