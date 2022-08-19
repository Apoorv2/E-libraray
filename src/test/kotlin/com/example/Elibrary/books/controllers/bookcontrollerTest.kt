package com.example.Elibrary.books.controllers

import com.example.Elibrary.books.models.Books
import com.example.Elibrary.books.services.bookservices
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable


internal class bookcontrollerTest{

    @MockK
    lateinit var bookservices: bookservices

    @MockK
    lateinit var books: Page<Books>

    @InjectMockKs
    lateinit var bookcontroller: bookcontroller

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun getBooks() {

        val pageRequest: Pageable = PageRequest.of(0, 100)
        every { bookservices.getBooks(pageRequest) } returns books
        val booklist = bookcontroller.getBooks(pageRequest)
        Assertions.assertEquals(booklist, books)
        verify(exactly = 1) { bookservices.getBooks(pageRequest) }
    }
    /*
    @Test
    fun insertBook(){

        val Book = Books(3, "adg", 200, "apoorv", "2022-05-21", "action", null)
        //val pageRequest: Pageable = PageRequest.of(0, 100)
        every { bookservices.insertbook(Book) } returns Book
        val bookinserted=bookcontroller.insertBook(null,"adg")
        Assertions.assertEquals(bookinserted,Book)
        verify(exactly=1) { bookservices.insertbook(Book) }
    }
 */

    @Test
    fun deleteBook() {
        val id =2;
        every { bookservices.deletebook(id) } returns Unit
        bookcontroller.deleteBook(id)
        verify(exactly = 1) { bookservices.deletebook(id) }
    }

    @Test
    fun getAllAuthor(){
        val authors:List<String> = listOf("Jack London","J K Rowling")
        every { bookservices.getAllAuthor() } returns authors
        val authorlist=bookcontroller.getAllAuthor()
        Assertions.assertEquals(authorlist,authors)
        verify (exactly=1){  bookservices.getAllAuthor()  }
    }

   // @Disabled
    @Test
    fun getListOfBook(){
       val name: String="Rudyard Kipling"
        val bookList:  List<Books> = listOf(Books(
            1,
            "The Jungle Book",
            323,
            "Rudyard Kipling",
            "2022-01-05",
            "fantasy",
            null,
        ))
        every { bookservices.getListOfBooks("Rudyard Kipling") } returns bookList
        val booksByAuthor=bookcontroller.getListOfBook(name)
        Assertions.assertEquals(booksByAuthor,bookList)
        verify (exactly=1){  bookservices.getListOfBooks(name)  }
    }

    @Test
    fun getBookSimilarTo(){
        val name : String ="The jungle Book"
        val bookList:  Page<Books> = PageImpl(listOf(Books(
            1,
            "The jungle Book",
            323,
            "Rudyard Kipling",
            "2022-01-05",
            "fantasy",
            null,
        )))
        val pageRequest: Pageable = PageRequest.of(0, 100)
        every { bookservices.getBookSimilarToo(name,pageRequest) } returns bookList
        val book =bookcontroller.getBookSimilarTo(BookByNameRequest(name),pageRequest)
        Assertions.assertEquals(book,bookList)
        verify(exactly=1){bookservices.getBookSimilarToo(name,pageRequest)}
    }

    @Test
    fun getAuthorSimilarTo(){
        val authorName: String ="Rudyard Kipling"
        val bookList:  Page<Books> = PageImpl(listOf(Books(
            1,
            "The jungle Book",
            323,
            "Rudyard Kipling",
            "2022-01-05",
            "fantasy",
            null,
        )))
        val pageRequest: Pageable = PageRequest.of(0, 100)
        every { bookservices.getBookByAuthor(authorName,pageRequest) } returns bookList
        val book =bookcontroller.getAuthorSimilarTo(bookByAuthorRequest(authorName),pageRequest)
        Assertions.assertEquals(book,bookList)
        verify(exactly=1){bookservices.getBookByAuthor(authorName,pageRequest)}
    }
    @Test
    fun getBookByCategory(){
        val category: String ="fantasy"
        val bookList:  Page<Books> = PageImpl(listOf(Books(
            1,
            "The jungle Book",
            323,
            "Rudyard Kipling",
            "2022-01-05",
            "fantasy",
            null,
        )))
        val pageRequest: Pageable = PageRequest.of(0, 100)
        every { bookservices.getBookByCategory(category,pageRequest) } returns bookList
        val book =bookcontroller.getBookByCategory(bookBycategory(category),pageRequest)
        Assertions.assertEquals(book,bookList)
        verify(exactly=1){bookservices.getBookByCategory(category,pageRequest)}
    }

}




