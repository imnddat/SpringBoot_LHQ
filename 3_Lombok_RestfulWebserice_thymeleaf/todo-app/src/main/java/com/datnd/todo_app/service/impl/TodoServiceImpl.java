package com.datnd.todo_app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datnd.todo_app.entity.Todo;
import com.datnd.todo_app.repository.TodoRepository;
import com.datnd.todo_app.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService {

    public static int increase = 0;

    @Autowired
    TodoRepository todoRepository;

    public void addTodo(String title) {
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setCompleted(false);
        //Todo todo = Todo.builder().title(title).completed(false).build();
        todoRepository.save(todo); // hàm save nếu truyền id sẽ là update, ko truyền sẽ là tạo mới
    }

    public List<Todo> getAllTodo() {
        return todoRepository.findAll();
    }

    public void deleteTodo(int id) {
        todoRepository.deleteById(id);
    }

    public void toggleTodo(int id) {
        Todo oldTodo = todoRepository.findById(id).get();
        oldTodo.setCompleted(!oldTodo.isCompleted());
        todoRepository.save(oldTodo);
    }

   
    
}
