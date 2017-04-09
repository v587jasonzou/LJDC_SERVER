package com.ljdc.pojo;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA
 * User:邹旭
 * Date:2017/4/8
 * Time:0:31
 * Desc:略
 */

@Entity
@Table(name = "admin", schema = "server_ljdc", catalog = "")
public class Admin {

    private int adminId;
    private String username;
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adminId", nullable = false)
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Basic
    @Column(name = "username", nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
