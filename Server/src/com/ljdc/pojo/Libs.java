package com.ljdc.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * User:邹旭
 * Date:2017/2/22 0022
 * Time:下午 7:57
 * Desc:略
 */
@Entity
@Table(name = "libs", schema = "server_ljdc", catalog = "")
public class Libs {
    private int libsId;
    private int totalNum;
    private String libName;
    private String tableName;
    private Date modified;

    //不参与持久化
    private int status;//同步状态
    private Date anchor;//同步锚点

//    @Id
//    @GeneratedValue(generator = "ASSIGN")
//    @GenericGenerator(name = "ASSIGN", strategy = "assigned")

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "libsId", nullable = false)
    public int getLibsId() {
        return libsId;
    }

    public void setLibsId(int libsId) {
        this.libsId = libsId;
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
    @Column(name = "libName")
    public String getLibName() {
        return libName;
    }

    public void setLibName(String libName) {
        this.libName = libName;
    }

    @Basic
    @Column(name = "tableName")
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
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
}
