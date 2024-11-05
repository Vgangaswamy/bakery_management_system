package edu.iit.sat.itmd4515.vgangaswamy.security;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="SEC_GROUP")
@NamedQuery(name = "Group.findAll", query = "select g from Group g")

public class Group {

    @Id
    private String groupName;
    private String groupDesc;
    @ManyToMany(mappedBy = "groups")
    private List<User> users = new ArrayList<>();

    public Group() {
    }
    public Group(String groupName, String groupDesc) {
        this.groupName = groupName;
        this.groupDesc = groupDesc;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupName='" + groupName + '\'' +
                ", groupDesc='" + groupDesc + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;
        Group group = (Group) o;
        return Objects.equals(getGroupName(), group.getGroupName());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getGroupName());
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }



}
