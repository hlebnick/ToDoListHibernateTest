package com.hlebnick.todolist.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "to_do_list")
public class ToDoListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "toDoListName")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "toDoListEntity")
    @Cascade(CascadeType.SAVE_UPDATE)
    private List<ToDoItemEntity> items;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ToDoItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ToDoItemEntity> items) {
        this.items = items;
    }
}
