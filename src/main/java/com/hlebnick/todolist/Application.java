package com.hlebnick.todolist;

import com.hlebnick.todolist.config.DataSourceConfig;
import com.hlebnick.todolist.dao.ToDoListDao;
import com.hlebnick.todolist.entities.ToDoItemEntity;
import com.hlebnick.todolist.entities.ToDoListEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collections;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);

        ToDoListDao listDao = (ToDoListDao) context.getBean("toDoListDaoImpl");

//        System.out.println(listDao.findAll());



        ToDoListEntity list = new ToDoListEntity();
        list.setName("My First List");

        ToDoItemEntity item = new ToDoItemEntity();
        item.setName("My First Item");
        item.setToDoListEntity(list);

        list.setItems(Collections.singletonList(item));

        listDao.createList(list);
    }
}
