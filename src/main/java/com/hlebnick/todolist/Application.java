package com.hlebnick.todolist;

import com.hlebnick.todolist.config.DataSourceConfig;
import com.hlebnick.todolist.dao.ToDoListDao;
import com.hlebnick.todolist.entities.ToDoListEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);

        ToDoListDao listDao = (ToDoListDao) context.getBean("toDoListDaoImpl");

        List<ToDoListEntity> lists = listDao.findAll();
        System.out.println(lists);
//
//        ToDoListEntity entity = new ToDoListEntity();
//        entity.setName("My First List");
//
//        listDao.createList(entity);
    }
}
