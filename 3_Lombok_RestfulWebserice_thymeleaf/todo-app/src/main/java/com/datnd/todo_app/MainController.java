package com.datnd.todo_app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpServletRequest;


@Controller
public class MainController {
    @Autowired
    TodoService todoService;

    @GetMapping("/hello")
    public String hello(){
        //xử lý logic ...
        return "hello";
    }

    @GetMapping("/list-todo")
    public String listTodo(Model model) {
        List<Todo> listTodos = todoService.getAllTodo();
        model.addAttribute("listTodos", listTodos);
        return "listTodo";
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