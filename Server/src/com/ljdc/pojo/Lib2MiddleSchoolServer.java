package com.ljdc.pojo;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA
 * User:邹旭
 * Date:2017/2/22 0022
 * Time:下午 7:57
 * Desc:略
 */
@Entity
@Table(name = "lib2", schema = "server_ljdc", catalog = "")
public class Lib2MiddleSchoolServer {
    private int lib2Id;
    private Collection<LearnLib2Server> learnLib2;
    private WordLibServer wordLib;

    public Lib2MiddleSchoolServer(int lib2Id) {
        this.lib2Id = lib2Id;
    }

    public Lib2MiddleSchoolServer() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lib2Id", nullable = false)
    public int getLib2Id() {
        return lib2Id;
    }

    public void setLib2Id(int lib2Id) {
        this.lib2Id = lib2Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lib2MiddleSchoolServer that = (Lib2MiddleSchoolServer) o;

        if (lib2Id != that.lib2Id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return lib2Id;
    }

    @OneToMany(mappedBy = "lib2",fetch = FetchType.LAZY)
    public Collection<LearnLib2Server> getLearnLib2() {
        return learnLib2;
    }

    public void setLearnLib2(Collection<LearnLib2Server> learnLib2) {
        this.learnLib2 = learnLib2;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wordId", referencedColumnName = "wordId", nullable = false)
    public WordLibServer getWordLib() {
        return wordLib;
    }

    public void setWordLib(WordLibServer wordLib) {
        this.wordLib = wordLib;
    }
}
