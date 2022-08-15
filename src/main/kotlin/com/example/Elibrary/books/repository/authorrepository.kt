package com.example.Elibrary.repository

import com.example.Elibrary.books.models.Author
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface authorrepository: PagingAndSortingRepository<Author, Int>