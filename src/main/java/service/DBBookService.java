package service;

import domain.Book;
import domain.validators.ValidatorException;
import repository.Repository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class DBBookService {
    private Repository<Long, Book> dbRepo;

    public DBBookService(Repository<Long,Book> dbRepo){
        this.dbRepo = dbRepo;
    }

    public void addBook(Book book) throws ValidatorException {
        dbRepo.save(book);
    }

    public void deleteBook(Long id) throws ValidatorException{
        dbRepo.delete(id);
    }

    public Set getAllBooks() {
        Iterable<Book> books = this.dbRepo.findAll();
        return (Set) StreamSupport.stream(books.spliterator(), false).collect(Collectors.toSet());
    }

    public void updateBook(Book book) throws ValidatorException{
        dbRepo.update(book);
    }






}
