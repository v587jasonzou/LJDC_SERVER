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
@Table(name = "word_evalution", schema = "server_ljdc", catalog = "")
public class WordEvaluation {
    private int evaluationId;
    private WordLibServer wordLib;
    private int level;//预估词汇的等级（1-9）

    private Date modified;
    //不参与持久化
    private int status;//同步状态
    private Date anchor;//同步锚点
    private int wordId; //传递外键ID

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evaluationId", nullable = false)
    public int getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(int evaluationId) {
        this.evaluationId = evaluationId;
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
    @Column(name = "level",nullable = false)//columnDefinition ="INT default 1"
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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
