package com.example.Elibrary.repository

import com.example.Elibrary.books.models.Books
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface booksrepository: JpaRepository<Books, Int> {
    @Query("SELECT a.authorName FROM Books a")
    fun getAllAuthors(): List<String>

    @Query("SELECT a FROM Books  a WHERE a.authorName=:name")
    fun getListOfBooks(@Param("name") name: String): List<Books>

    @Query("from Books b where b.name like ?1")
    fun findBookSimilarTo(name: String, pageable: Pageable): Page<Books>

    @Query("from Books b where b.authorName like ?1")
    fun findBookByAuthor(name: String, pageable: Pageable): Page<Books>

    @Query("from Books b where b.category like ?1")
    fun findBookByCategory (name: String, pageable: Pageable): Page<Books>
}

