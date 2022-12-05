package com.bookbuffs.bookbuffs.service;

import com.bookbuffs.bookbuffs.model.Book;
import com.bookbuffs.bookbuffs.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {

    private final AuthService authService;
    private final BookRepository bookRepository;

    public Page<Book> listAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Page<Book> listAllByUser(Pageable pageable) {
        return bookRepository.findByUser(authService.getCurrentUser(), pageable);
    }

    public void save(Book book){
        book.setUser(authService.getCurrentUser());
        bookRepository.save(book);

    }

    public void replace(Book book){
      bookRepository.save(book);
    }


}
