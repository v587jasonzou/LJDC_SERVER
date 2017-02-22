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
@Table(name = "user_server", schema = "server_ljdc", catalog = "")
public class UserServer {
    private int userId;
    private String phone;
    private String password;
    private String email;
    private String niceName;
    private String headImageUrl;
    private Collection<LearnLib1Server> learnLib1ServersByUserId;
    private Collection<LearnLib2Server> learnLib2ServersByUserId;
    private Collection<WordDevelopmentServer> wordDevelopmentServersByUserId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 20)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 20)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "nice_name", nullable = true, length = 20)
    public String getNiceName() {
        return niceName;
    }

    public void setNiceName(String niceName) {
        this.niceName = niceName;
    }

    @Basic
    @Column(name = "head_image_url", nullable = true, length = 20)
    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserServer that = (UserServer) o;

        if (userId != that.userId) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (niceName != null ? !niceName.equals(that.niceName) : that.niceName != null) return false;
        if (headImageUrl != null ? !headImageUrl.equals(that.headImageUrl) : that.headImageUrl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (niceName != null ? niceName.hashCode() : 0);
        result = 31 * result + (headImageUrl != null ? headImageUrl.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userServerByUserId")
    public Collection<LearnLib1Server> getLearnLib1ServersByUserId() {
        return learnLib1ServersByUserId;
    }

    public void setLearnLib1ServersByUserId(Collection<LearnLib1Server> learnLib1ServersByUserId) {
        this.learnLib1ServersByUserId = learnLib1ServersByUserId;
    }

    @OneToMany(mappedBy = "userServerByUserId")
    public Collection<LearnLib2Server> getLearnLib2ServersByUserId() {
        return learnLib2ServersByUserId;
    }

    public void setLearnLib2ServersByUserId(Collection<LearnLib2Server> learnLib2ServersByUserId) {
        this.learnLib2ServersByUserId = learnLib2ServersByUserId;
    }

    @OneToMany(mappedBy = "userServerByUserId")
    public Collection<WordDevelopmentServer> getWordDevelopmentServersByUserId() {
        return wordDevelopmentServersByUserId;
    }

    public void setWordDevelopmentServersByUserId(Collection<WordDevelopmentServer> wordDevelopmentServersByUserId) {
        this.wordDevelopmentServersByUserId = wordDevelopmentServersByUserId;
    }
}
