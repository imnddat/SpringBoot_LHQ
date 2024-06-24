package com.datnd.real_world_app.model.article.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTOResponse {
    private String username;
    private String bio;
    private String image;
    private boolean following;
}
