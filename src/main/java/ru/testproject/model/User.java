package ru.testproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Анатолий on 08.06.2016.
 */
@Entity
@Table(name = "users")
public class User extends BasedEntity {

    @Column(name = "name")
    @NotEmpty(message = "Must not be empty!")
    private String name;

    @Column(name = "login")
    @NotEmpty(message = "Must not be empty!")
    private String login;

    @Column(name = "password")
    @NotEmpty(message = "Must not be empty!")
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).+)", message = "Must have at least one capital letter and number!")
    private String password;

    /*@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = {@JoinColumn(name = "user_id")},
    inverseJoinColumns = {@JoinColumn(name = "role_id")})*/

    @Transient
    private Set<Role> roles=new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private Set<RoleUser> roleUserSet = new HashSet<>();

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        if (roleUserSet.size()>0)
            for (RoleUser role_user:roleUserSet){
            roles.add(role_user.getRole());
            }
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;

    }

    public Set<RoleUser> getRoleUserSet() {
        return roleUserSet;
    }

    public void setRoleUserSet(Set<RoleUser> roleUserSet) {
        this.roleUserSet = roleUserSet;
    }
}
