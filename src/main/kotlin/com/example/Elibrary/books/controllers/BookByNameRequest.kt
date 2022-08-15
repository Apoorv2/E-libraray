package com.example.Elibrary.books.controllers

data class BookByNameRequest(var name: String) {
    constructor(): this(
        ""
    )
}
