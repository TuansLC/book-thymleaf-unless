package com.fpt.repository;

import com.fpt.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByName(String name);
}
