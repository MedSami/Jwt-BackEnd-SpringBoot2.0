package com.sami.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AppRole {
    @Id
    @GeneratedValue
    private Long id;
    private String roleName;


    public AppRole() {

    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getRoleName() {
        return roleName;
    }


    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    public AppRole(Long id, String roleName) {
        super();
        this.id = id;
        this.roleName = roleName;
    }


}
