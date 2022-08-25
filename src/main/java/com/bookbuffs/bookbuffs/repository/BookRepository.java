package com.bookbuffs.bookbuffs.repository;

import com.bookbuffs.bookbuffs.model.Book;
import com.bookbuffs.bookbuffs.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findByUser(User user, Pageable pageable);

}
