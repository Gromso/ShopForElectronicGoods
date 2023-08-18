package com.example.ShopForElectronicGoods.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class ApplicationUser implements UserDetails {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String password_hash;

    @Column(name = "forename", nullable = false)
    private String forename;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phone_number;

    @Column(name = "postal_address", nullable = false)
    private String postal_address;

    @OneToMany( mappedBy="user")
    private Set<Cart> cart;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> authorities;

    public ApplicationUser(){
        super();
        this.authorities = new HashSet<Role>();
    }

    public ApplicationUser(Integer user_id, String email, String password_hash, String forename, String surname, String phone_number, String postal_address, Set<Role> authorities) {
        super();
        this.user_id = user_id;
        this.email = email;
        this.password_hash = password_hash;
        this.forename = forename;
        this.surname = surname;
        this.phone_number = phone_number;
        this.postal_address = postal_address;
        this.authorities = authorities;
    }

    public ApplicationUser(Integer user_id,String email, String password_hash, Set<Role> authorities){
        super();
        this.user_id =user_id;
        this.email= email;
        this.password_hash = password_hash;
        this.authorities = authorities;
    }

    public ApplicationUser(String email, String password, Set<Role> authority) {
        super();
        this.email = email;
        this.password_hash =password;
        this.authorities =authority;
    }

    public ApplicationUser(String email, String password, Set<Role> authority, String forename, String surname, String phone_number, String postal_address) {
        super();
        this.email = email;
        this.password_hash = password;
        this.authorities =authority;
        this.forename =forename;
        this.surname =surname;
        this.phone_number =phone_number;
        this.postal_address = postal_address;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return this.password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPostal_address() {
        return postal_address;
    }

    public void setPostal_address(String postal_address) {
        this.postal_address = postal_address;
    }

    public Set<Cart> getCart() {
        return cart;
    }

    public void setCart(Set<Cart> cart) {
        this.cart = cart;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
