package com.example.Footballl.model.dto;

import jakarta.persistence.Column;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {
    private Long id;
    private String name;
    private Integer age;
    private String position;
    private String club;
    private Double transferValue;
}