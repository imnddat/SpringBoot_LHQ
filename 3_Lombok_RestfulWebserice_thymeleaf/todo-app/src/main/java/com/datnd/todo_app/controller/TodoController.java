package com.datnd.todo_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.datnd.todo_app.entity.Todo;
import com.datnd.todo_app.service.TodoService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class TodoController {
    @Autowired
    TodoService todoService;

    @GetMapping("/list-todo")
    public String listTodo(Model model) {
        List<Todo> listTodos = todoService.getAllTodo();
        model.addAttribute("listTodos", listTodos);
        return "todo/listTodo";
    }
    
    @PostMapping(value = "/add-todo")
    public String addTodo(HttpServletRequest req){
        String title = req.getParameter("title");
        todoService.addTodo(title);
        return "redirect:/list-todo";
    }
    
    @GetMapping("/delete-todo")
    public String deleteTodo(HttpServletRequest req){
        int id = Integer.parseInt(req.getParameter("id"));
        todoService.deleteTodo(id);
        return "redirect:/list-todo";
    }

    @GetMapping("/complete-todo")
    public String toggleTodo(HttpServletRequest req){
        int id = Integer.parseInt(req.getParameter("id"));
        todoService.toggleTodo(id);
        return "redirect:/list-todo";
    }
}
