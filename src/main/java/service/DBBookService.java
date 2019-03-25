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

public class DBBookService {
    private PagingRepository<Long, Book> dbRepo;

    //COPY THIS TO THE OTHER SERVICES
    private int page = 0;
    private int size = 1;


    /**
     * constructor for the book service
     * @param repository - the repository
     */
    public DBBookService(PagingRepository<Long, Book> repository) {
        this.dbRepo = repository;
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
            Page<Book> bookPage = dbRepo.findAll(pageable);
            page = bookPage.nextPageable().getPageNumber();
            return bookPage.getContent().collect(Collectors.toSet());
        }catch (IndexOutOfBoundsException ex) {
            page = 0;
            return new HashSet<>();
        }
    }

    public void addBook(Book book) throws ValidatorException {
        dbRepo.save(Optional.ofNullable(book));
    }

    public void deleteBook(Long id) throws ValidatorException{
        dbRepo.delete(Optional.ofNullable(id));
    }

    public Set getAllBooks() {
        Iterable<Book> books = this.dbRepo.findAll();
        return (Set) StreamSupport.stream(books.spliterator(), false).collect(Collectors.toSet());
    }

    public void updateBook(Book book) throws ValidatorException{
        dbRepo.update(Optional.ofNullable(book));
    }






}
