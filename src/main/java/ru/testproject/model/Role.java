package ru.testproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Анатолий on 08.06.2016.
 */
@Entity
@Table(name = "roles")
public class Role extends BasedEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "role")
    @JsonIgnore
    private Set<RoleUser> role_user;

    public Role() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<RoleUser> getRole_user() {
        return role_user;
    }

    public void setRole_user(Set<RoleUser> role_user) {
        this.role_user = role_user;
    }
}
