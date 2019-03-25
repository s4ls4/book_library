package service;


import domain.Book;
import domain.validators.ValidatorException;
import repository.Paging.Page;
import repository.Paging.PageRequest;
import repository.Paging.Pageable;
import repository.Paging.PagingRepository;
import repository.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 *  author: Stefi Nicoara
 */
public class BookService {
    private PagingRepository<Long, Book> repository;

    //COPY THIS TO THE OTHER SERVICES
    private int page = 0;
    private int size = 1;


    /**
     * constructor for the book service
     * @param repository - the repository
     */
    public BookService(PagingRepository<Long, Book> repository) {
        this.repository = repository;
    }

    //COPY THIS TO THE OTHER SERVICES
    public void setPageSize(int size) {
        this.size = size;
        this.page = 0;
    }

    //COPY THIS TO THE OTHER SERVICES
    public Set<Book> getNextBook() {
        Pageable pageable = PageRequest.of(size, page);
        try{
            Page<Book> bookPage = repository.findAll(pageable);
            page = bookPage.nextPageable().getPageNumber();
            return bookPage.getContent().collect(Collectors.toSet());
        }catch (IndexOutOfBoundsException ex) {
            page = 0;
            return new HashSet<>();
        }
    }

    /**
     * Function that adds a book
     * @param book a book object
     * @throws ValidatorException
     */
    public void addBook(Book book) throws ValidatorException {
        repository.save(Optional.ofNullable(book));
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
        repository.delete(Optional.ofNullable(id));
    }

    /**
     * Function that updates a book
     * @param book the new object
     * @throws ValidatorException
     */
    public void updateBook(Book book) throws ValidatorException{
        repository.update(Optional.ofNullable(book));
    }
}
