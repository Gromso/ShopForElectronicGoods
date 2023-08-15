package com.example.ShopForElectronicGoods.models;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Integer role_id;

    @Column(name = "authority", nullable = false)
    private String authority;


    public Role(){
        super();
    }

    public Role(String authority) {
        this.authority = authority;
    }

    public Role(Integer role_id, String authority){
        this.role_id = role_id;
        this.authority = authority;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
