package com.example.Elibrary.books.services

import com.example.Elibrary.books.models.Author
import com.example.Elibrary.repository.authorrepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.annotation.Id
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service


@Service
class authorSevices {
    @Autowired
    lateinit var repository:authorrepository

    fun getAuthorList(pageable: Pageable): Page<Author> {
        return repository.findAll(pageable)
    }
    fun insertAuthor(author: Author): Author = repository.save(author)
   fun deleteAuthor(id: Int) = repository.deleteById(id)
}