package com.bookStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookStore.entity.BookList;

@Repository
public interface MyBookRepository extends JpaRepository<BookList, Integer>{

}
