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
@Table(name = "word_lib", schema = "server_ljdc", catalog = "")
public class WordLibServer {
    private int wordId;
    private String word;
    private String pronStrEn;
    private String pronUrlEn;
    private String pronStrUs;
    private String pronUrlUs;
    private String pos1;
    private String acceptation1;
    private String pos2;
    private String acceptation2;
    private String pos3;
    private String acceptation3;
    private String pos4;
    private String acceptation4;
    private String sentEn1;
    private String sentTrans1;
    private String sentEn2;
    private String sentTrans2;
    private String sentEn3;
    private String sentTrans3;
    private String sentEn4;
    private String sentTrans4;
    private Collection<Lib1EnglishGrand4CoreServer> lib1;
    private Collection<Lib2MiddleSchoolServer> lib2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wordId", nullable = false)
    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    @Basic
    @Column(name = "word", nullable = false, length = 20)
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Basic
    @Column(name = "pronStrEn", nullable = false, length = 20)
    public String getPronStrEn() {
        return pronStrEn;
    }

    public void setPronStrEn(String pronStrEn) {
        this.pronStrEn = pronStrEn;
    }

    @Basic
    @Column(name = "pronUrlEn", nullable = true, length = 255)
    public String getPronUrlEn() {
        return pronUrlEn;
    }

    public void setPronUrlEn(String pronUrlEn) {
        this.pronUrlEn = pronUrlEn;
    }

    @Basic
    @Column(name = "pronStrUs", nullable = false, length = 20)
    public String getPronStrUs() {
        return pronStrUs;
    }

    public void setPronStrUs(String pronStrUs) {
        this.pronStrUs = pronStrUs;
    }

    @Basic
    @Column(name = "pronUrlUs", nullable = true, length = 255)
    public String getPronUrlUs() {
        return pronUrlUs;
    }

    public void setPronUrlUs(String pronUrlUs) {
        this.pronUrlUs = pronUrlUs;
    }

    @Basic
    @Column(name = "pos1", nullable = true, length = 20)
    public String getPos1() {
        return pos1;
    }

    public void setPos1(String pos1) {
        this.pos1 = pos1;
    }

    @Basic
    @Column(name = "acceptation1", nullable = true, length = 20)
    public String getAcceptation1() {
        return acceptation1;
    }

    public void setAcceptation1(String acceptation1) {
        this.acceptation1 = acceptation1;
    }

    @Basic
    @Column(name = "pos2", nullable = true, length = 20)
    public String getPos2() {
        return pos2;
    }

    public void setPos2(String pos2) {
        this.pos2 = pos2;
    }

    @Basic
    @Column(name = "acceptation2", nullable = true, length = 20)
    public String getAcceptation2() {
        return acceptation2;
    }

    public void setAcceptation2(String acceptation2) {
        this.acceptation2 = acceptation2;
    }

    @Basic
    @Column(name = "pos3", nullable = true, length = 20)
    public String getPos3() {
        return pos3;
    }

    public void setPos3(String pos3) {
        this.pos3 = pos3;
    }

    @Basic
    @Column(name = "acceptation3", nullable = true, length = 20)
    public String getAcceptation3() {
        return acceptation3;
    }

    public void setAcceptation3(String acceptation3) {
        this.acceptation3 = acceptation3;
    }

    @Basic
    @Column(name = "pos4", nullable = true, length = 20)
    public String getPos4() {
        return pos4;
    }

    public void setPos4(String pos4) {
        this.pos4 = pos4;
    }

    @Basic
    @Column(name = "acceptation4", nullable = true, length = 20)
    public String getAcceptation4() {
        return acceptation4;
    }

    public void setAcceptation4(String acceptation4) {
        this.acceptation4 = acceptation4;
    }

    @Basic
    @Column(name = "sentEn1", nullable = true, length = 255)
    public String getSentEn1() {
        return sentEn1;
    }

    public void setSentEn1(String sentEn1) {
        this.sentEn1 = sentEn1;
    }

    @Basic
    @Column(name = "sentTrans1", nullable = true, length = 255)
    public String getSentTrans1() {
        return sentTrans1;
    }

    public void setSentTrans1(String sentTrans1) {
        this.sentTrans1 = sentTrans1;
    }

    @Basic
    @Column(name = "sentEn2", nullable = true, length = 255)
    public String getSentEn2() {
        return sentEn2;
    }

    public void setSentEn2(String sentEn2) {
        this.sentEn2 = sentEn2;
    }

    @Basic
    @Column(name = "sentTrans2", nullable = true, length = 255)
    public String getSentTrans2() {
        return sentTrans2;
    }

    public void setSentTrans2(String sentTrans2) {
        this.sentTrans2 = sentTrans2;
    }

    @Basic
    @Column(name = "sentEn3", nullable = true, length = 255)
    public String getSentEn3() {
        return sentEn3;
    }

    public void setSentEn3(String sentEn3) {
        this.sentEn3 = sentEn3;
    }

    @Basic
    @Column(name = "sentTrans3", nullable = true, length = 255)
    public String getSentTrans3() {
        return sentTrans3;
    }

    public void setSentTrans3(String sentTrans3) {
        this.sentTrans3 = sentTrans3;
    }

    @Basic
    @Column(name = "sentEn4", nullable = true, length = 255)
    public String getSentEn4() {
        return sentEn4;
    }

    public void setSentEn4(String sentEn4) {
        this.sentEn4 = sentEn4;
    }

    @Basic
    @Column(name = "sentTrans4", nullable = true, length = 255)
    public String getSentTrans4() {
        return sentTrans4;
    }

    public void setSentTrans4(String sentTrans4) {
        this.sentTrans4 = sentTrans4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordLibServer that = (WordLibServer) o;

        if (wordId != that.wordId) return false;
        if (word != null ? !word.equals(that.word) : that.word != null) return false;
        if (pronStrEn != null ? !pronStrEn.equals(that.pronStrEn) : that.pronStrEn != null) return false;
        if (pronUrlEn != null ? !pronUrlEn.equals(that.pronUrlEn) : that.pronUrlEn != null) return false;
        if (pronStrUs != null ? !pronStrUs.equals(that.pronStrUs) : that.pronStrUs != null) return false;
        if (pronUrlUs != null ? !pronUrlUs.equals(that.pronUrlUs) : that.pronUrlUs != null) return false;
        if (pos1 != null ? !pos1.equals(that.pos1) : that.pos1 != null) return false;
        if (acceptation1 != null ? !acceptation1.equals(that.acceptation1) : that.acceptation1 != null) return false;
        if (pos2 != null ? !pos2.equals(that.pos2) : that.pos2 != null) return false;
        if (acceptation2 != null ? !acceptation2.equals(that.acceptation2) : that.acceptation2 != null) return false;
        if (pos3 != null ? !pos3.equals(that.pos3) : that.pos3 != null) return false;
        if (acceptation3 != null ? !acceptation3.equals(that.acceptation3) : that.acceptation3 != null) return false;
        if (pos4 != null ? !pos4.equals(that.pos4) : that.pos4 != null) return false;
        if (acceptation4 != null ? !acceptation4.equals(that.acceptation4) : that.acceptation4 != null) return false;
        if (sentEn1 != null ? !sentEn1.equals(that.sentEn1) : that.sentEn1 != null) return false;
        if (sentTrans1 != null ? !sentTrans1.equals(that.sentTrans1) : that.sentTrans1 != null) return false;
        if (sentEn2 != null ? !sentEn2.equals(that.sentEn2) : that.sentEn2 != null) return false;
        if (sentTrans2 != null ? !sentTrans2.equals(that.sentTrans2) : that.sentTrans2 != null) return false;
        if (sentEn3 != null ? !sentEn3.equals(that.sentEn3) : that.sentEn3 != null) return false;
        if (sentTrans3 != null ? !sentTrans3.equals(that.sentTrans3) : that.sentTrans3 != null) return false;
        if (sentEn4 != null ? !sentEn4.equals(that.sentEn4) : that.sentEn4 != null) return false;
        if (sentTrans4 != null ? !sentTrans4.equals(that.sentTrans4) : that.sentTrans4 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = wordId;
        result = 31 * result + (word != null ? word.hashCode() : 0);
        result = 31 * result + (pronStrEn != null ? pronStrEn.hashCode() : 0);
        result = 31 * result + (pronUrlEn != null ? pronUrlEn.hashCode() : 0);
        result = 31 * result + (pronStrUs != null ? pronStrUs.hashCode() : 0);
        result = 31 * result + (pronUrlUs != null ? pronUrlUs.hashCode() : 0);
        result = 31 * result + (pos1 != null ? pos1.hashCode() : 0);
        result = 31 * result + (acceptation1 != null ? acceptation1.hashCode() : 0);
        result = 31 * result + (pos2 != null ? pos2.hashCode() : 0);
        result = 31 * result + (acceptation2 != null ? acceptation2.hashCode() : 0);
        result = 31 * result + (pos3 != null ? pos3.hashCode() : 0);
        result = 31 * result + (acceptation3 != null ? acceptation3.hashCode() : 0);
        result = 31 * result + (pos4 != null ? pos4.hashCode() : 0);
        result = 31 * result + (acceptation4 != null ? acceptation4.hashCode() : 0);
        result = 31 * result + (sentEn1 != null ? sentEn1.hashCode() : 0);
        result = 31 * result + (sentTrans1 != null ? sentTrans1.hashCode() : 0);
        result = 31 * result + (sentEn2 != null ? sentEn2.hashCode() : 0);
        result = 31 * result + (sentTrans2 != null ? sentTrans2.hashCode() : 0);
        result = 31 * result + (sentEn3 != null ? sentEn3.hashCode() : 0);
        result = 31 * result + (sentTrans3 != null ? sentTrans3.hashCode() : 0);
        result = 31 * result + (sentEn4 != null ? sentEn4.hashCode() : 0);
        result = 31 * result + (sentTrans4 != null ? sentTrans4.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "wordLib")
    public Collection<Lib1EnglishGrand4CoreServer> getLib1() {
        return lib1;
    }

    public void setLib1(Collection<Lib1EnglishGrand4CoreServer> lib1) {
        this.lib1 = lib1;
    }

    @OneToMany(mappedBy = "wordLib")
    public Collection<Lib2MiddleSchoolServer> getLib2() {
        return lib2;
    }

    public void setLib2(Collection<Lib2MiddleSchoolServer> lib2) {
        this.lib2 = lib2;
    }
}
