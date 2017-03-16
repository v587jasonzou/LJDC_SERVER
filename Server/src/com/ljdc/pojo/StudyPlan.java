package com.ljdc.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA
 * User:邹旭
 * Date:2017/3/9 0009
 * Time:下午 11:41
 * Desc:略
 */
@SuppressWarnings("ALL")
@Entity
@Table(name = "study_plan", schema = "server_ljdc", catalog = "")
public class StudyPlan {

    private String planId;
    private UserServer user;
    private String currentLib;
    private int totalNum;
    private int leftNum;
    private int planOfDay;
    private int doOfDay;
    private Date finishDate;
    private Date modified;

    //不参与持久化
    private int status;//同步状态
    private Date anchor;//同步锚点
    private int userId;//网络传输
    private String oldId;//需要删除的不同步的记录ID


    @Id
    @GeneratedValue(generator = "ASSIGN")
    @GenericGenerator(name = "ASSIGN", strategy = "assigned")
    @Column(name = "planId", nullable = false)
    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    public UserServer getUser() {
        return user;
    }

    public void setUser(UserServer user) {
        this.user = user;
    }

    @Basic
    @Column(name = "currentLib")
    public String getCurrentLib() {
        return currentLib;
    }

    public void setCurrentLib(String currentLib) {
        this.currentLib = currentLib;
    }

    @Basic
    @Column(name = "totalNum")
    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    @Basic
    @Column(name = "leftNum")
    public int getLeftNum() {
        return leftNum;
    }

    public void setLeftNum(int leftNum) {
        this.leftNum = leftNum;
    }

    @Basic
    @Column(name = "planOfDay")
    public int getPlanOfDay() {
        return planOfDay;
    }

    public void setPlanOfDay(int planOfDay) {
        this.planOfDay = planOfDay;
    }

    @Basic
    @Column(name = "doOfDay")
    public int getDoOfDay() {
        return doOfDay;
    }

    public void setDoOfDay(int doOfDay) {
        this.doOfDay = doOfDay;
    }

    @Basic
    @Column(name = "finishDate")
    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    @Basic
    @Column(name = "modified")
    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Transient
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
    public String getOldId() {
        return oldId;
    }

    public void setOldId(String oldId) {
        this.oldId = oldId;
    }

    @Override
    public String toString() {
        return "StudyPlan{" +
                "planId=" + planId +
                ", userID_Server=" + user.getUserId() +
                ", currentLib='" + currentLib + '\'' +
                ", totalNum=" + totalNum +
                ", leftNum=" + leftNum +
                ", planOfDay=" + planOfDay +
                ", doOfDay=" + doOfDay +
                ", finishDate=" + finishDate +
                ", modified=" + modified +
                ", status=" + status +
                ", anchor=" + anchor +
                ", userId_Client=" + userId +
                ", oldId='" + oldId + '\'' +
                '}';
    }
}
