package com.example.Elibrary.books.services
import com.example.Elibrary.books.models.Books
import com.example.Elibrary.repository.booksrepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service



@Service
 class bookservices {

 @Autowired
 lateinit var repository: booksrepository

 @Cacheable(value = ["Books"])
 fun getBooks(pageable: Pageable): Page<Books> = repository.findAll(pageable)

 fun insertbook(books: Books): Books = repository.save(books)

 fun deletebook(id: Int)= repository.deleteById(id)

 fun getAllAuthor(): List<String> = repository.getAllAuthors()

 fun getListOfBooks(name: String): List<Books> = repository.getListOfBooks(name)

 fun getBookSimilarToo(name: String, pageable: Pageable): Page<Books> {
  return repository.findBookSimilarTo(name, pageable)
 }
  fun getBookByAuthor(name: String, pageable: Pageable) = repository.findBookByAuthor(name, pageable)

 fun getBookByCategory( name:String,pageable: Pageable) = repository.findBookByCategory(name, pageable)

}