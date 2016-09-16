package com.hlebnick.todolist.dao;

import com.hlebnick.todolist.entities.ToDoListEntity;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ToDoListDaoImpl extends GenericDaoImpl<ToDoListEntity, Long>  implements ToDoListDao {

    public ToDoListDaoImpl() {
        super(ToDoListEntity.class);
    }

    @Override
    public Long createList(ToDoListEntity toDoListEntity) {
        return create(toDoListEntity);
    }

    @Override
    public ToDoListEntity findById(long id) {
        return read(id);
    }

    @Override
    public List<ToDoListEntity> findAll() {
        return getSession().createCriteria(getType().getCanonicalName()).list();
    }
}
