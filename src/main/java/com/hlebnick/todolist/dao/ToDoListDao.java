package com.hlebnick.todolist.dao;

import com.hlebnick.todolist.entities.ToDoListEntity;

import java.util.List;

public interface ToDoListDao {

    Long createList(ToDoListEntity toDoListEntity);

    ToDoListEntity findById(long id);

    List<ToDoListEntity> findAll();
}
