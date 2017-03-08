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
@SuppressWarnings("ALL")
@Entity
@Table(name = "word_development", schema = "server_ljdc", catalog = "")
public class WordDevelopmentServer {
    private String wordDevId;
    private Integer wordsIncreaseNum;
    private Date wordIncreaseDate;
    private Integer graspLevel;
    private Date modified;
    private UserServer user;

    private int statusModify;//接收客户端数据
    private Date anchor;//接收客户端数据
    private int userId; //用户ID
    private String oldId;//需要删除的不同步的记录ID

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
    public String getOldId() {
        return oldId;
    }

    public void setOldId(String oldId) {
        this.oldId = oldId;
    }

    @Id
    @GeneratedValue(generator = "ASSIGN")
    @GenericGenerator(name = "ASSIGN", strategy = "assigned")
    @Column(name = "wordDevId", nullable = false)
    public String getWordDevId() {
        return wordDevId;
    }

    public void setWordDevId(String wordDevId) {
        this.wordDevId = wordDevId;
    }

    @Basic
    @Column(name = "wordsIncreaseNum", nullable = true)
    public Integer getWordsIncreaseNum() {
        return wordsIncreaseNum;
    }

    public void setWordsIncreaseNum(Integer wordsIncreaseNum) {
        this.wordsIncreaseNum = wordsIncreaseNum;
    }

    @Basic
    @Column(name = "wordIncreaseDate", nullable = true)
    public Date getWordIncreaseDate() {
        return wordIncreaseDate;
    }

    public void setWordIncreaseDate(Date wordIncreaseDate) {
        this.wordIncreaseDate = wordIncreaseDate;
    }

    @Basic
    @Column(name = "graspLevel", nullable = true)
    public Integer getGraspLevel() {
        return graspLevel;
    }

    public void setGraspLevel(Integer graspLevel) {
        this.graspLevel = graspLevel;
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

        WordDevelopmentServer that = (WordDevelopmentServer) o;

        if (wordDevId != that.wordDevId) return false;
        if (wordsIncreaseNum != null ? !wordsIncreaseNum.equals(that.wordsIncreaseNum) : that.wordsIncreaseNum != null)
            return false;
        if (wordIncreaseDate != null ? !wordIncreaseDate.equals(that.wordIncreaseDate) : that.wordIncreaseDate != null)
            return false;
        if (graspLevel != null ? !graspLevel.equals(that.graspLevel) : that.graspLevel != null) return false;
        if (modified != null ? !modified.equals(that.modified) : that.modified != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = wordDevId.hashCode();
        result = 31 * result + (wordsIncreaseNum != null ? wordsIncreaseNum.hashCode() : 0);
        result = 31 * result + (wordIncreaseDate != null ? wordIncreaseDate.hashCode() : 0);
        result = 31 * result + (graspLevel != null ? graspLevel.hashCode() : 0);
        result = 31 * result + (modified != null ? modified.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    public UserServer getUser() {
        return user;
    }

    public void setUser(UserServer userServerByUserId) {
        this.user = userServerByUserId;
    }

    @Override
    public String toString() {
        return "WordDevelopmentServer{" +
                "wordDevId='" + wordDevId + '\'' +
                ", wordsIncreaseNum=" + wordsIncreaseNum +
                ", wordIncreaseDate=" + wordIncreaseDate +
                ", graspLevel=" + graspLevel +
                ", modified=" + modified +
                ", statusModify=" + statusModify +
                ", anchor=" + anchor +
                ", userId_Client=" + userId +
                ", oldId='" + oldId + '\'' +
                ",userID_Server="+user.getUserId()+
                '}';
    }
}
