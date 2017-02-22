package com.ljdc.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA
 * User:邹旭
 * Date:2017/2/22 0022
 * Time:下午 7:57
 * Desc:略
 */
@Entity
@Table(name = "learn_lib1_server", schema = "server_ljdc", catalog = "")
public class LearnLib1Server {
    private int learnLib1Id;
    private int graspLevel;
    private Timestamp updataTime;
    private Timestamp modified;
    private Lib1EnglishGrand4CoreServer lib1EnglishGrand4CoreServerByLib1Id;
    private UserServer userServerByUserId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "learn_lib1_id", nullable = false)
    public int getLearnLib1Id() {
        return learnLib1Id;
    }

    public void setLearnLib1Id(int learnLib1Id) {
        this.learnLib1Id = learnLib1Id;
    }

    @Basic
    @Column(name = "grasp_level", nullable = false)
    public int getGraspLevel() {
        return graspLevel;
    }

    public void setGraspLevel(int graspLevel) {
        this.graspLevel = graspLevel;
    }

    @Basic
    @Column(name = "updata_time", nullable = false)
    public Timestamp getUpdataTime() {
        return updataTime;
    }

    public void setUpdataTime(Timestamp updataTime) {
        this.updataTime = updataTime;
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

        LearnLib1Server that = (LearnLib1Server) o;

        if (learnLib1Id != that.learnLib1Id) return false;
        if (graspLevel != that.graspLevel) return false;
        if (updataTime != null ? !updataTime.equals(that.updataTime) : that.updataTime != null) return false;
        if (modified != null ? !modified.equals(that.modified) : that.modified != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = learnLib1Id;
        result = 31 * result + graspLevel;
        result = 31 * result + (updataTime != null ? updataTime.hashCode() : 0);
        result = 31 * result + (modified != null ? modified.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "lib1_id", referencedColumnName = "lib1_id")
    public Lib1EnglishGrand4CoreServer getLib1EnglishGrand4CoreServerByLib1Id() {
        return lib1EnglishGrand4CoreServerByLib1Id;
    }

    public void setLib1EnglishGrand4CoreServerByLib1Id(Lib1EnglishGrand4CoreServer lib1EnglishGrand4CoreServerByLib1Id) {
        this.lib1EnglishGrand4CoreServerByLib1Id = lib1EnglishGrand4CoreServerByLib1Id;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    public UserServer getUserServerByUserId() {
        return userServerByUserId;
    }

    public void setUserServerByUserId(UserServer userServerByUserId) {
        this.userServerByUserId = userServerByUserId;
    }
}
