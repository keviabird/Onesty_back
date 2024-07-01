package com.onesty.services.management.persistence;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;

@Entity
@Table(name = "tests", indexes = {@Index(name = "test_unique_idx", unique = true, columnList = "rubricId,testId")})
@Data
public class TestEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private int version;

    private Long rubricId;
    private String name;
    private Boolean isBlocked;
    private String imageUrl;
}
