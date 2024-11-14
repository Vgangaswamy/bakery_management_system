package edu.iit.sat.itmd4515.vgangaswamy.security;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name="SEC_USER")
@EntityListeners(UserPasswordHash.class)
@NamedQuery(name = "User.findAll", query = "select u from User u")
public class User {

    @Id
    @NotBlank(message = "Must enter a username")
    private String username;
    @NotBlank(message = "Must enter a password")
    private String password;
    @ManyToMany
    @JoinTable(name = "SEC_USER_GROUPS",
                       joinColumns = @JoinColumn(name = "USERNAME"),
                       inverseJoinColumns = @JoinColumn(name = "GROUPNAME"))
    private List<Group> groups = new ArrayList<>();

    public User() {
    }

    public void addGroup(Group g){
        this.groups.add(g);
        g.getUsers().add(this);
    }
    public void removeGroup(Group g){
        this.groups.remove(g);
        g.getUsers().remove(this);
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", groups=" + groups +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
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



}
