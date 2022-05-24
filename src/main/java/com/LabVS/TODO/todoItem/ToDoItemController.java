package com.LabVS.TODO.todoItem;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/todos")
@AllArgsConstructor
public class ToDoItemController {
    private final ToDoItemService todoItemService;
    @Autowired ToDoItemRepository toDoItemRepository;

    @GetMapping
    public Iterable<ToDoItem> getTodos() {
        return todoItemService.getTodos();
    }

    @GetMapping(produces = "application/json", path = "/{name}")
    Optional<ToDoItem> getTodoItem(@PathVariable String name){
        return toDoItemRepository.findById(name);
    }


    @PostMapping
            (consumes = "application/json", produces = "application/json")
    public String createTodo (@RequestBody ToDoItem item){

        Optional<ToDoItem> maybeExistingItem = toDoItemRepository.findById(item.getTodo());
        boolean ItemExists = maybeExistingItem.isPresent();
        if(ItemExists) throw new IllegalStateException("Todo already exists" );

        toDoItemRepository.save(item);
        return item.getTodo();

    }

    @DeleteMapping(consumes="application/json", produces ="application/json",path= "/{name}")
    public boolean deleteToDo(@PathVariable String name) {
        return todoItemService.deleteToDoItem(name);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ToDoItem> updatePriority(@RequestBody ToDoItem toDoItem){
        Optional<ToDoItem> maybeToDoItem = toDoItemRepository.findById(toDoItem.getTodo());
        if (maybeToDoItem.isPresent()){
            ToDoItem _toDoItem = maybeToDoItem.get();
            _toDoItem.setPriority(toDoItem.getPriority());
            return new ResponseEntity<>(toDoItemRepository.save(_toDoItem), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
