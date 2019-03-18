package service;
import domain.Book;

import domain.validators.Validator;
import domain.validators.ValidatorException;
import repository.XMLRepository;

public class XMLBookService {
    private XMLRepository xmlRepo;


    public XMLBookService(XMLRepository xmlRepo) {
        this.xmlRepo = xmlRepo;
    }

    public void addMovie(Book book) throws ValidatorException {

        xmlRepo.save(book);
    }


}
