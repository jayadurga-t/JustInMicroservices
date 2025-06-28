package com.durga.loans.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@MappedSuperclass
@Setter @Getter @ToString
public class BaseEntity {

    @Column(insertable = false)
    private LocalDateTime createdAt;

    @Column(insertable = false)
    private String createdBy;

    @Column(updatable = false)
    private LocalDateTime updatedAt;

    @Column(updatable = false)
    private String updatedBy;

}
