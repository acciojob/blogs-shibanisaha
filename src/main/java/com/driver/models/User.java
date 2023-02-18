package com.driver.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    //bidirectional connection between user & blog;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Blog> blogCreated = new ArrayList<>();

    ///////////////constructor///////////////////////////

    public User() {
    }

    //////////////////////getter & setter//////////////////////

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Blog> getBlogCreated() {
        return blogCreated;
    }

    public void setBlogCreated(List<Blog> blogCreated) {
        this.blogCreated = blogCreated;
    }
}