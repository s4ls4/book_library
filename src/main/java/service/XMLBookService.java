package service;
import domain.Book;

import domain.validators.Validator;
import domain.validators.ValidatorException;
import repository.Repository;
import repository.XMLRepository;

public class XMLBookService {
    private Repository<Long, Book> xmlRepo;


    public XMLBookService(Repository<Long, Book> xmlRepo) {
        this.xmlRepo = xmlRepo;
    }

    public void addBook(Book book) throws Exception {

        xmlRepo.save(book);
    }


}
