package com.laundry.moving.modal.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "hello", schema = "laundry", catalog = "")
public class HelloEntity {
    private int id;
    private String name;
    private Integer userId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "userId")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HelloEntity that = (HelloEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, userId);
    }
}
