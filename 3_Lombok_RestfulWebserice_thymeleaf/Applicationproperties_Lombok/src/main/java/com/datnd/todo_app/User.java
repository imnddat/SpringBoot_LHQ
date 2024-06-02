package com.datnd.todo_app;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
// @NoArgsConstructor
// @AllArgsConstructor
@Builder
@ToString
public class User {
    private int id;
    private String name;
    private int age;
    private double salary;
}
