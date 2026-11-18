package com.example.book.controller;
import java.util.List; import java.util.concurrent.CopyOnWriteArrayList; import org.springframework.web.bind.annotation.*; import com.example.book.model.Book;
@RestController @RequestMapping("/books")
public class BookController {
 private final List<Book> books=new CopyOnWriteArrayList<>(List.of(new Book(1,"The Alchemist","Paulo Coelho"),new Book(2,"Wings of Fire","A. P. J. Abdul Kalam")));
 @GetMapping public List<Book> getBooks(){return books;}
 @PostMapping public Book addBook(@RequestBody Book book){books.add(book);return book;}
}
