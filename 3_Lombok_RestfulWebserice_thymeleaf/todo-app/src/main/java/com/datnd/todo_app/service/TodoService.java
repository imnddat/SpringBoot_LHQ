package com.datnd.todo_app.service;

import java.util.List;

import com.datnd.todo_app.entity.Todo;

public interface TodoService {
    public void addTodo(String title);
    public List<Todo> getAllTodo();
    public void deleteTodo(int id);
    public void toggleTodo(int id);
}
