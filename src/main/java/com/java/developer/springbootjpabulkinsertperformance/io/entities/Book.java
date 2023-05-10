package com.java.developer.springbootjpabulkinsertperformance.io.entities;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "books")
public class Book {

  @Id
  @GeneratedValue(generator = "uuid4")
  @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id;

  private String name;

  private Integer Price;

}
