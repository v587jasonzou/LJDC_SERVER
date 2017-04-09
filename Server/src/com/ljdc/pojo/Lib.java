package com.ljdc.pojo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * User:邹旭
 * DateTime:2017/4/1 14:52
 * Desc:词汇量预估
 */
@Entity
@Table(name = "lib", schema = "server_ljdc", catalog = "")
public class Lib {
    private int libId;
    private WordLibServer wordLib;
    private String  libName;//所属词库

    private Date modified;
    //不参与持久化
    private int status;//同步状态
    private Date anchor;//同步锚点
    private int wordId; //传递外键ID

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "libId", nullable = false)
    public int getLibId() {
        return libId;
    }

    public void setLibId(int libId) {
        this.libId = libId;
    }


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wordId", referencedColumnName = "wordId")
    public WordLibServer getWordLib() {
        return wordLib;
    }

    public void setWordLib(WordLibServer wordLib) {
        this.wordLib = wordLib;
    }

    @Basic
    @Column(name = "libName",nullable = false)
    public String getLibName() {
        return libName;
    }
    public void setLibName(String libName) {
        this.libName = libName;
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
    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }
}
