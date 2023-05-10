package com.java.developer.springbootjpabulkinsertperformance;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.java.developer.springbootjpabulkinsertperformance.io.entities.Book;
import com.java.developer.springbootjpabulkinsertperformance.io.repositories.BookRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class SpringBootJpaBulkInsertPerformanceApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootJpaBulkInsertPerformanceApplication.class, args);
  }

  @Autowired
  private BookRepository repository;

  @Value("${spring.jpa.properties.hibernate.jdbc.batch-size}")
  private int batchSize;

  @EventListener(ApplicationReadyEvent.class)
  public void doSomethingAfterStartup() {

    int totalObjects = 100000;

    long start = System.currentTimeMillis();
    List<Book> books = IntStream.range(0, totalObjects)
        .mapToObj(val -> Book.builder()
            .name("books: " + UUID.randomUUID())
            .Price(val)
            .build())
        .collect(Collectors.toList());

    log.info("Finished creating " + totalObjects + " objects in memory in:" + (System.currentTimeMillis() - start) / 1000);

    start = System.currentTimeMillis();
    log.info("Inserting ..........");

    // repository.saveAll(books);

    for (int i = 0; i < totalObjects; i += batchSize) {
      if (i + batchSize > totalObjects) {
        List<Book> books1 = books.subList(i, totalObjects - 1);
        repository.saveAll(books1);
        break;
      }
      List<Book> books1 = books.subList(i, i + batchSize);
      repository.saveAll(books1);
    }

    log.info("Finished inserting " + totalObjects + " objects in :" + (System.currentTimeMillis() - start));
  }

}
