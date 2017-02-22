package com.ljdc.pojo;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA
 * User:邹旭
 * Date:2017/2/22 0022
 * Time:下午 7:57
 * Desc:略
 */
@Entity
@Table(name = "word_development_server", schema = "server_ljdc", catalog = "")
public class WordDevelopmentServer {
    private int wordDevId;
    private Integer wordsIncreaseNum;
    private Date wordIncreaseDate;
    private Integer graspLevel;
    private Timestamp modified;
    private UserServer userServerByUserId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "word_dev_id", nullable = false)
    public int getWordDevId() {
        return wordDevId;
    }

    public void setWordDevId(int wordDevId) {
        this.wordDevId = wordDevId;
    }

    @Basic
    @Column(name = "words_increase_num", nullable = true)
    public Integer getWordsIncreaseNum() {
        return wordsIncreaseNum;
    }

    public void setWordsIncreaseNum(Integer wordsIncreaseNum) {
        this.wordsIncreaseNum = wordsIncreaseNum;
    }

    @Basic
    @Column(name = "word_increase_date", nullable = true)
    public Date getWordIncreaseDate() {
        return wordIncreaseDate;
    }

    public void setWordIncreaseDate(Date wordIncreaseDate) {
        this.wordIncreaseDate = wordIncreaseDate;
    }

    @Basic
    @Column(name = "grasp_level", nullable = true)
    public Integer getGraspLevel() {
        return graspLevel;
    }

    public void setGraspLevel(Integer graspLevel) {
        this.graspLevel = graspLevel;
    }

    @Basic
    @Column(name = "modified", nullable = false)
    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
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
        int result = wordDevId;
        result = 31 * result + (wordsIncreaseNum != null ? wordsIncreaseNum.hashCode() : 0);
        result = 31 * result + (wordIncreaseDate != null ? wordIncreaseDate.hashCode() : 0);
        result = 31 * result + (graspLevel != null ? graspLevel.hashCode() : 0);
        result = 31 * result + (modified != null ? modified.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public UserServer getUserServerByUserId() {
        return userServerByUserId;
    }

    public void setUserServerByUserId(UserServer userServerByUserId) {
        this.userServerByUserId = userServerByUserId;
    }
}
