package service;


import domain.Book;
import domain.validators.ValidatorException;
import repository.Repository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 *  author: Stefi Nicoara
 */
public class BookService {
    private Repository<Long, Book> repository;

    public BookService(Repository<Long, Book> repository) {
        this.repository = repository;
    }

    public void addBook(Book book) throws ValidatorException {
        repository.save(book);
    }

    public Set<Book> getAllBooks() {
        Iterable<Book> books = this.repository.findAll();
        return (Set) StreamSupport.stream(books.spliterator(), false).collect(Collectors.toSet());
    }

    public void deleteBook(Long id) {
        repository.delete(id);
    }
}
