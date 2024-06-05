package com.datnd.user_management.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class CustomError {
    private String code;
    private String message;
}
