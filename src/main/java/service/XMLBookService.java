package service;
import domain.Book;

import domain.validators.Validator;
import domain.validators.ValidatorException;
import repository.Repository;
import repository.XMLRepository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class XMLBookService {
    private Repository<Long, Book> xmlRepo;


    public XMLBookService(Repository<Long, Book> xmlRepo) {
        this.xmlRepo = xmlRepo;
    }

    public void addBook(Book book) {

        xmlRepo.save(book);
    }

    public Set printAllBooks() {
        Iterable<Book> books = this.xmlRepo.findAll();
        return (Set) StreamSupport.stream(books.spliterator(), false).collect(Collectors.toSet());
    }

    public void deleteBook(Long ID) {
        xmlRepo.delete(ID);
    }


}
