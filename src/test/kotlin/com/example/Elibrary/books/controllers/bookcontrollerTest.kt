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
    fun getBooks(){

        val pageRequest: Pageable = PageRequest.of(0, 100)
        every { bookservices.getBooks(pageRequest) } returns books
        val booklist=bookcontroller.getBooks(pageRequest)
        Assertions.assertEquals(booklist,books)
        verify(exactly=1) { bookservices.getBooks(pageRequest) }
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
    /*
    @Test
    fun deletebook(){
        val id =2;
        every { bookservices.deletebook(id) } returns Unit
        deletebook(id)
    }
     */
}




