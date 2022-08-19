package com.example.Elibrary.books.models

import org.springframework.data.domain.Page
import java.io.Serializable
import java.sql.Date
import javax.persistence.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity
@Table(name="books")

data class Books(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "isbn_no")
    val isbnNumber: Int = 1,

    @Column(name = "name")
    val name: String = " ",

    @Column(name = "number_of_pages")
    val numberOfPages: Int = 0,

    @Column(name = "authorName")
    val authorName: String = " ",

    @Column(name = "addedOn")
    val addedOn: String = " ",

    @Column(name = "category")
    val category: String = " ",

    @Column(name = "file")
    @Lob
    val file: ByteArray? = null

)

