package com.ljdc.pojo;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA
 * User:邹旭
 * Date:2017/4/10
 * Time:0:28
 * Desc:略
 */
@Entity
@Table(name = "removed_lib", schema = "server_ljdc", catalog = "")
public class RemovedLib {
    private int removeId;
    private String libName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getRemoveId() {
        return removeId;
    }

    public void setRemoveId(int removeId) {
        this.removeId = removeId;
    }

    @Basic
    @Column(name = "libName",nullable = false)
    public String getLibName() {
        return libName;
    }

    public void setLibName(String libName) {
        this.libName = libName;
    }
}
