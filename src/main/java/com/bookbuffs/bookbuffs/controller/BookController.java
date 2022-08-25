package com.bookbuffs.bookbuffs.controller;

import com.bookbuffs.bookbuffs.model.Book;
import com.bookbuffs.bookbuffs.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<Page<Book>> list(Pageable pageable) {
        //  log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(bookService.listAll(pageable));
    }


    @GetMapping("by-user")
    public ResponseEntity<Page<Book>> listByUser(Pageable pageable) {
        //  log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(bookService.listAllByUser(pageable));
    }

    @PostMapping
    public ResponseEntity saveBook(@RequestBody Book book){
        bookService.save(book);
        return new ResponseEntity(HttpStatus.CREATED);
    }



}
