package service;

import domain.Book;
import domain.validators.ValidatorException;
import repository.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class DBBookService {
    private Repository<Long, Book> dbRepo;

    public DBBookService(Repository<Long,Book> dbRepo){
        this.dbRepo = dbRepo;
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
