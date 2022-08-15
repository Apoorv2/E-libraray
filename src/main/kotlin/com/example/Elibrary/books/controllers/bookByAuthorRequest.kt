package com.example.Elibrary.books.controllers

data class bookByAuthorRequest(var authorName: String) {

    constructor(): this(
        ""
    )
}
