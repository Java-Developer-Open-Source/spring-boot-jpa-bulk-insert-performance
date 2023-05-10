package com.java.developer.springbootjpabulkinsertperformance.io.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.developer.springbootjpabulkinsertperformance.io.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

}
