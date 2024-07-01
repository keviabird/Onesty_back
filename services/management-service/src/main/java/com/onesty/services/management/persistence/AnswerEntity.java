package com.onesty.services.management.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;

@Entity
@Table(name = "answers")
@Data
public class AnswerEntity {

    @Id
    @GeneratedValue
    private int id;

    @Version
    private int version;

    private int questionId;
    private String value;
    private Long score;
}
