package com.fpt.service.admin;

import com.fpt.entity.Book;

import java.util.List;

public interface BookService {


    List<Book> search(String name);
    List<Book> bookComingSoon(int status,long createdAt);

    Book getById(long id);

    Book save(Book book);

    Book update(long id, Book updateBook);

    boolean delete(long id);


}
