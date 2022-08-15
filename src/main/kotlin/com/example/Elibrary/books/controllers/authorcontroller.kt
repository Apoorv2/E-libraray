package com.example.Elibrary.books.controllers

import com.example.Elibrary.books.services.authorSevices
import com.example.Elibrary.books.services.bookservices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/author")
class authorcontroller {
    @Autowired
    lateinit var service: authorSevices

    @GetMapping(
        produces = arrayOf(MediaType.APPLICATION_JSON_VALUE) )
    fun getAuthorList(pageable: Pageable): Any? {
        try {
            return service.getAuthorList(pageable)
        }catch (e: Exception){
            return e.localizedMessage
        }
    }

    @DeleteMapping(
        value = ["/{id}"],
        produces = arrayOf(MediaType.APPLICATION_JSON_VALUE) )
    fun deleteAuthor(@PathVariable(name = "id") id: Int ):Any  = service.deleteAuthor(id)

}