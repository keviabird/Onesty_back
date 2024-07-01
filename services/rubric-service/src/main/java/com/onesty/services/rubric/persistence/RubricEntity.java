package com.onesty.services.rubric.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;

@Entity
@Table(name = "rubrics")
@Data
public class RubricEntity {

    @Id
    @GeneratedValue
    private int id;

    @Version
    private int version;

    private int content;
    private int score;
}
