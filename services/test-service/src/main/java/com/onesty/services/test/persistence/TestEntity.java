package com.onesty.services.test.persistence;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tests", indexes = {@Index(name = "test_unique_idx", unique = true, columnList = "rubricId,testId")})
@Data
public class TestEntity {

    @Id
    @GeneratedValue
    private int id;

    @Version
    private int version;

    private int rubricId;
    private int testId;
    private String name;
}
