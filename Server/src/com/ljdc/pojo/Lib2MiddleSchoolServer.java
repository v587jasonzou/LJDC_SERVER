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
@Table(name = "lib2_middle_school_server", schema = "server_ljdc", catalog = "")
public class Lib2MiddleSchoolServer {
    private int lib2Id;
    private Collection<LearnLib2Server> learnLib2ServersByLib2Id;
    private WordLibServer wordLibServerByWordId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lib2_id", nullable = false)
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

    @OneToMany(mappedBy = "lib2MiddleSchoolServerByLib2Id")
    public Collection<LearnLib2Server> getLearnLib2ServersByLib2Id() {
        return learnLib2ServersByLib2Id;
    }

    public void setLearnLib2ServersByLib2Id(Collection<LearnLib2Server> learnLib2ServersByLib2Id) {
        this.learnLib2ServersByLib2Id = learnLib2ServersByLib2Id;
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
