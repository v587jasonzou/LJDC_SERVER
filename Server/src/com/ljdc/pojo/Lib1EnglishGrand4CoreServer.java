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
@Table(name = "lib1_english_grand_4_core_server", schema = "server_ljdc", catalog = "")
public class Lib1EnglishGrand4CoreServer {
    private int lib1Id;
    private Collection<LearnLib1Server> learnLib1ServersByLib1Id;
    private WordLibServer wordLibServerByWordId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lib1_id", nullable = false)
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

    @OneToMany(mappedBy = "lib1EnglishGrand4CoreServerByLib1Id")
    public Collection<LearnLib1Server> getLearnLib1ServersByLib1Id() {
        return learnLib1ServersByLib1Id;
    }

    public void setLearnLib1ServersByLib1Id(Collection<LearnLib1Server> learnLib1ServersByLib1Id) {
        this.learnLib1ServersByLib1Id = learnLib1ServersByLib1Id;
    }

    @ManyToOne
    @JoinColumn(name = "word_id", referencedColumnName = "word_id", nullable = false)
    public WordLibServer getWordLibServerByWordId() {
        return wordLibServerByWordId;
    }

    public void setWordLibServerByWordId(WordLibServer wordLibServerByWordId) {
        this.wordLibServerByWordId = wordLibServerByWordId;
    }
}
