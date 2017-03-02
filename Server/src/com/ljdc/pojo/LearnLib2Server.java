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
@Table(name = "learn_lib2", schema = "server_ljdc", catalog = "")
public class LearnLib2Server {
    private int learnLib2Id;
    private int graspLevel;
    private Timestamp updataTime;
    private Timestamp modified;
    private Lib2MiddleSchoolServer lib2;
    private UserServer user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "learnLib2Id", nullable = false)
    public int getLearnLib2Id() {
        return learnLib2Id;
    }

    public void setLearnLib2Id(int learnLib2Id) {
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

        LearnLib2Server that = (LearnLib2Server) o;

        if (learnLib2Id != that.learnLib2Id) return false;
        if (graspLevel != that.graspLevel) return false;
        if (updataTime != null ? !updataTime.equals(that.updataTime) : that.updataTime != null) return false;
        if (modified != null ? !modified.equals(that.modified) : that.modified != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = learnLib2Id;
        result = 31 * result + graspLevel;
        result = 31 * result + (updataTime != null ? updataTime.hashCode() : 0);
        result = 31 * result + (modified != null ? modified.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "lib2Id", referencedColumnName = "lib2Id")
    public Lib2MiddleSchoolServer getLib2() {
        return lib2;
    }

    public void setLib2(Lib2MiddleSchoolServer lib2) {
        this.lib2 = lib2;
    }

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    public UserServer getUser() {
        return user;
    }

    public void setUser(UserServer user) {
        this.user = user;
    }
}
