package com.example.Elibrary.books.controllers

import com.example.Elibrary.books.models.Author
import com.example.Elibrary.books.models.Books
import com.example.Elibrary.books.services.authorSevices
import com.example.Elibrary.books.services.bookservices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
@RequestMapping("/api/books")

class bookcontroller{
    @Autowired
    lateinit var service: bookservices

    @Autowired
    lateinit var authorService:authorSevices

    @GetMapping(
        produces = arrayOf(MediaType.APPLICATION_JSON_VALUE) )
    fun getBooks(pageable: Pageable): Any? {
        try {
            return service.getBooks(pageable)
        }catch (e: Exception){
            return e.localizedMessage
        }
    }

    @PutMapping(
        produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
//        consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE) )
        consumes = ["multipart/form-data"]
    )
    fun insertBook(
        @RequestParam(value = "file") file: MultipartFile,
        @RequestParam(value = "name") name: String,
        @RequestParam(value = "numberOfPages") numberOfPages: Int,
        @RequestParam(value = "authorName") authorName: String,
        @RequestParam(value = "addedOn") addedOn: String,
        @RequestParam(value = "category") category: String
    ): Any {
        try {
            authorService.insertAuthor(Author(name=authorName, nameOfBook = name))
            return service.insertbook(Books(name = name, numberOfPages = numberOfPages, authorName = authorName ,addedOn=addedOn, category=category, file= file.bytes))
        } catch (e: Exception) {
            return e.localizedMessage
        }

    }

    @DeleteMapping("/{id}")
    fun deletebook(@PathVariable(name="id") id: Int):Any {
        try {
            return service.deletebook(id)
        }catch (e: Exception){
            return e.localizedMessage
        }
    }
    @GetMapping("/author")
    fun getAllAuthor():List<String> =service.getAllAuthor()

    @GetMapping("/list")
    fun getListOfBook(@RequestParam name:String):List<Books> =service.getListOfBooks(name)

    @GetMapping("/name")
    fun getBookSimilarTo(
        @RequestBody payload: BookByNameRequest, pageable: Pageable
    ): Any{
        try {
            return service.getBookSimilarToo(payload.name, pageable)
        }catch (e: Exception){
            return e.localizedMessage
        }
    }

    @GetMapping("/author_name")
    fun getAuthorSimilarTo(
        @RequestBody payload: bookByAuthorRequest, pageable: Pageable
    ): Any{
        try {
            return service.getBookByAuthor(payload.authorName, pageable)
        }catch (e: Exception){
            return e.localizedMessage
        }
    }

    @GetMapping("/category")
    fun getBookByCategory(
        @RequestBody payload: bookBycategory, pageable: Pageable
    ): Any{
        try {
            return service.getBookByCategory(payload.category, pageable)
        }catch (e: Exception){
            return e.localizedMessage
        }
    }
}