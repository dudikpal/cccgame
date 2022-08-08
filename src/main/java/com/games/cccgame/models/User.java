package com.games.cccgame.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Document("users"/*,
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "username"),
           @UniqueConstraint(columnNames = "email")
       }*/)
public class User {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private String garageId;

    @NotBlank
    @Size(max = 120)
    private String password;

    /*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
               joinColumns = @JoinColumn(name = "user_id"),
               inverseJoinColumns = @JoinColumn(name = "role_id"))*/
    private Set <Role> roles = new HashSet <>();

    public User(String username, String email, String garageId, String password) {
        this.username = username;
        this.email = email;
        this.garageId = garageId;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGarageId() {
        return garageId;
    }

    public void setGarageId(String garageId) {
        this.garageId = garageId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set <Role> getRoles() {
        return roles;
    }

    public void setRoles(Set <Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
            "id='" + id + '\'' +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", roles=" + roles +
            '}';
    }
}
