package domain.validators;

import domain.Book;

public class BookValidator implements Validator<Book> {
    @Override
    public void validate(Book book) throws ValidatorException
    {
        int sn = Integer.parseInt(book.getSerialNumber());
        if(sn < 0) {
            throw new ValidatorException("Cannot have a negative serial number");
        }
        if(book.getSerialNumber() == null) {
            throw new ValidatorException("serial number must not be null");
        }
        if(book.getName() == null) {
            throw new ValidatorException("name must not be null");
        }
        if(!book.getName().matches("[a-zA-Z]+")) {
            throw new ValidatorException("Name must be made out of letters!");
        }
        if(book.getAuthor() == null) {
            throw new ValidatorException("author must not be null");
        }
        if(!book.getAuthor().matches("[a-zA-Z]+")) {
            throw new ValidatorException("Author must be made out of letters!");
        }
        if(book.getPrice() < 0) {
            throw new ValidatorException("price must be greater than 0");
        }
        if(book.getPrice() > 1000) {
            throw new ValidatorException("price must be greater than 0");
        }
    }
}
