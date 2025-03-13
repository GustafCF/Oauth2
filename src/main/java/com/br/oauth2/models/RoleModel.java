package com.br.oauth2.models;

import com.br.oauth2.models.enums.RoleStatus;
import com.br.oauth2.models.enums.RoleStatusConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TB_ROLES")
public class RoleModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private String name;
    private String description;
    @Convert(converter = RoleStatusConverter.class)
    private RoleStatus roleStatus;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private List<UserModel> users = new ArrayList<>();

    public RoleModel() {
    }

    public RoleModel(Long id, String name, String description, RoleStatus roleStatus) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.roleStatus = roleStatus;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RoleStatus getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(RoleStatus roleStatus) {
        this.roleStatus = roleStatus;
    }

    public List<UserModel> getUsers() {
        return users;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RoleModel roleModel = (RoleModel) o;
        return Objects.equals(id, roleModel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
