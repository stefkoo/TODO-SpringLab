package com.LabVS.TODO.todoItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ToDoItemRepository extends JpaRepository<ToDoItem, String> {

    List<ToDoItem> findAll();

    @Override
    void deleteById(String s);

    @Override
    Optional<ToDoItem> findById(String s);
}
