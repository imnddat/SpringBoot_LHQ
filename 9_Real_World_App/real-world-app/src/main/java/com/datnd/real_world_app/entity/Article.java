package com.datnd.real_world_app.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "article_tbl")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String slug;
    private String title;
    private String description;
    private String body;
    private String tagList;
    private Date createdAt;
    private Date updatedAt;
    private boolean favorited;
    private int favoritesCount;

    public List<String> getTagList() {

        return Arrays.asList(this.tagList.split(";"));
    }

    public void setTagList(List<String> tagList) {
        StringBuilder str = new StringBuilder();
        for (String tag : tagList) {
            str.append(tag).append(";");
        }
        this.tagList = str.substring(0, str.length() - 1).toString();
    }

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToMany(mappedBy = "articleFavorited")
    private Set<User> userFavorited;
   



}
