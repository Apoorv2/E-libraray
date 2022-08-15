package com.example.Elibrary.books.models

import javax.persistence.*
@Entity
@Table(name = "author")
 data class Author(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    val id: Int=1,

    @Column(name="Name")
    val name : String = " ",

    @Column(name="nameOfBook")
    val nameOfBook: String=" ",
)
