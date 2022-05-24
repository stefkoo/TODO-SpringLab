package com.LabVS.TODO.todoItem;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ToDoItemService {

    private final ToDoItemRepository toDoItemRepository;

    public Iterable<ToDoItem> getTodos() {
        return toDoItemRepository.findAll();
    }

    public boolean deleteToDoItem(String todo) {

        toDoItemRepository.deleteById(todo);

        if(toDoItemRepository.findById(todo).isPresent()) return false;

        return true;
    }

}
