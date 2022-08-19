package com.example.Elibrary.books.services

import com.example.Elibrary.books.models.Books
import com.example.Elibrary.repository.booksrepository
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

internal class bookservicesTest{

    @MockK
    lateinit var bookRepository: booksrepository

    @MockK
    lateinit var books: Page<Books>

    @InjectMockKs
    lateinit var bookservices: bookservices

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun getbooks() {
        //val books = mutableListOf(Books(3,"adg",200,"apoorv","2022-05-21","action",null))
        val pageRequest: Pageable = PageRequest.of(0, 100)
    every { bookRepository.findAll(pageRequest) } returns books

        val book =bookservices.getBooks(pageRequest);
        Assertions.assertEquals(book,books)
        verify(exactly = 1) {  bookRepository.findAll(pageRequest) }
    }

    @Test
    fun insertBook() {
        val books = Books(4, "adg", 200, "apoorv", "2022-05-21", "action", null,)
       every { bookRepository.save(books) } returns books
        val book=bookservices.insertbook(books)
        Assertions.assertEquals(book,books)
        verify(exactly=1){bookRepository.save(books)}
    }

    @Test
    fun deleteBook(){
        val id = 2;
        every{ bookRepository.deleteById(id)} returns Unit
        bookservices.deletebook(id)
        verify(exactly = 1) { bookRepository.deleteById(id) }
    }

    @Test
    fun getAllAuthor() {
       val authors:  List<String> = listOf("J K Rowling" ,"Jack London")
       every { bookRepository.getAllAuthors() } returns authors
       val authorList = bookservices.getAllAuthor()
        Assertions.assertEquals(authorList,authors)
        verify (exactly = 1){ bookRepository.getAllAuthors() }
    }

    @Test
    fun getListOfBooks() {
        val bookList:  List<Books> = listOf(Books(
            1,
            "The jungle Book",
            323,
            "Rudyard Kipling",
            "2022-01-05",
            "fanstasy",
            null,
        ))
        every { bookRepository.getListOfBooks("The jungle Book") } returns bookList
        val listOfBooks = bookservices.getListOfBooks("The jungle Book")
        Assertions.assertEquals(listOfBooks,bookList)
        verify (exactly = 1){ bookRepository.getListOfBooks("The jungle Book") }
    }


    @Test
    fun getBookSimilarToo(){
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
        every {bookRepository.findBookSimilarTo("The jungle Book",pageRequest)} returns bookList
        val book =bookservices.getBookSimilarToo("The jungle Book",pageRequest)
        Assertions.assertEquals(book,bookList)
       verify (exactly =1){ bookRepository.findBookSimilarTo("The jungle Book",pageRequest) }
    }

    @Test
    fun getBookByAuthor(){
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
        every {bookRepository.findBookByAuthor("Rudyard kipling",pageRequest)} returns bookList
        val book = bookservices.getBookByAuthor("Rudyard kipling",pageRequest)
        Assertions.assertEquals(book,bookList)
        verify (exactly =1){ bookRepository.findBookByAuthor("Rudyard kipling",pageRequest) }
    }

    @Test
   fun getBookByCategory(){
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
        every {bookRepository.findBookByCategory("fantasy",pageRequest)} returns bookList
        val book = bookservices.getBookByCategory("fantasy",pageRequest)
        Assertions.assertEquals(book,bookList)
        verify (exactly =1){ bookRepository.findBookByCategory("fantasy",pageRequest) }
   }

}



