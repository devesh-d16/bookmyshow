package com.devesh.bookmyshow.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDate;

@Document(indexName = "movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDocument {

    @Id
    private String id;
    private String title;
    private String genre;
    private String language;
    private String description;
    private Double rating;
    private Long duration;
    private LocalDate releaseDate;
}
