package com.example.book.model;
public class Book {
 private int bookId; private String bookName; private String author;
 public Book(){} public Book(int id,String name,String author){this.bookId=id;this.bookName=name;this.author=author;}
 public int getBookId(){return bookId;} public void setBookId(int id){this.bookId=id;}
 public String getBookName(){return bookName;} public void setBookName(String name){this.bookName=name;}
 public String getAuthor(){return author;} public void setAuthor(String author){this.author=author;}
}
