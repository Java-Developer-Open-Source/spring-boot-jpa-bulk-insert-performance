package com.java.developer.springbootjpabulkinsertperformance.io.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.developer.springbootjpabulkinsertperformance.io.entities.Book;

public interface BookRepository extends JpaRepository<Book, String> {

}
