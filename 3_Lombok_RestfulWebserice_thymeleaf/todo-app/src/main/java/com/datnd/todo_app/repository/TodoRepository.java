package com.datnd.todo_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datnd.todo_app.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Integer> { //JpaRepository<Bảng/entity muốn thao tác, kiểu dữ liệu của khóa chính của bảng đó>
   
}
