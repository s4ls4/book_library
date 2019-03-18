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

    /**
     * constructor for the book service
     * @param repository - the repository
     */
    public BookService(Repository<Long, Book> repository) {
        this.repository = repository;
    }

    /**
     * Function that adds a book
     * @param book a book object
     * @throws ValidatorException
     */
    public void addBook(Book book) throws ValidatorException {
        repository.save(book);
    }

    /**
     * Function that allows access to all the entities
     * @return a stream with all the book objects
     */
    public Set getAllBooks() {
        Iterable<Book> books = this.repository.findAll();
        return (Set) StreamSupport.stream(books.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     * Function that deletes a book
     * @param id the id of the required book
     */
    public void deleteBook(Long id) {
        repository.delete(id);
    }

    /**
     * Function that updates a book
     * @param book the new object
     * @throws ValidatorException
     */
    public void updateBook(Book book) throws ValidatorException{
        repository.update(book);
    }
}
