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
@Table(name = "lib1", schema = "server_ljdc", catalog = "")
public class Lib1EnglishGrand4CoreServer {
    private int lib1Id;
    private Collection<LearnLib1Server> learnLib1;
    private WordLibServer wordLib;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lib1Id", nullable = false)
    public int getLib1Id() {
        return lib1Id;
    }

    public void setLib1Id(int lib1Id) {
        this.lib1Id = lib1Id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lib1EnglishGrand4CoreServer that = (Lib1EnglishGrand4CoreServer) o;

        if (lib1Id != that.lib1Id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return lib1Id;
    }

    @OneToMany(mappedBy = "lib1")
    public Collection<LearnLib1Server> getLearnLib1() {
        return learnLib1;
    }

    public void setLearnLib1(Collection<LearnLib1Server> learnLib1) {
        this.learnLib1 = learnLib1;
    }

    @ManyToOne
    @JoinColumn(name = "wordId", referencedColumnName = "wordId", nullable = false)
    public WordLibServer getWordLib() {
        return wordLib;
    }

    public void setWordLib(WordLibServer wordLib) {
        this.wordLib = wordLib;
    }
}
