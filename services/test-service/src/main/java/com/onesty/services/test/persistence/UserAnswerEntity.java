package com.onesty.services.test.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;

@Entity
@Table(name = "user_answers")
@Data
public class UserAnswerEntity {

    @Id
    @GeneratedValue
    private int id;

    @Version
    private int version;

    private String userId;
    private int questionId;
    private int answerId;
}
