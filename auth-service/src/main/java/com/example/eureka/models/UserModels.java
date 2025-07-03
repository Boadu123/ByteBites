package com.example.eureka.models;


import com.example.eureka.enums.Roles;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;


@Entity
@Table(name = "users")
public class UserModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;


    @Enumerated(EnumType.STRING)
    private Roles roles;


    public UserModels() {}

    public UserModels(Long id, String name, String email, String password, Roles roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public static class Builder {
        private String name;
        private String email;
        private String password;
        private Roles roles;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder roles(Roles roles) {
            this.roles = roles;
            return this;
        }

        public UserModels build() {
            UserModels user = new UserModels();
            user.setName(this.name);
            user.setEmail(this.email);
            user.setPassword(this.password);
            user.setRoles(this.roles);
            return user;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
