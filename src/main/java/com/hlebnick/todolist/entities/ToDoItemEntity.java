package com.hlebnick.todolist.entities;

import javax.persistence.*;

@Entity
public class ToDoItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "toDoItemName")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "list_id", nullable = false)
    private ToDoListEntity toDoListEntity;

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

    public ToDoListEntity getToDoListEntity() {
        return toDoListEntity;
    }

    public void setToDoListEntity(ToDoListEntity toDoListEntity) {
        this.toDoListEntity = toDoListEntity;
    }
}
