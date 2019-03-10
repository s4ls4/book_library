package domain.validators;

import domain.Book;

public class BookValidator implements Validator<Book> {
    @Override
    public void validate(Book book) throws ValidatorException
    {
//        if(book.getSerialNumber() != null) {
//            throw new ValidatorException("serial number must not be null");
//        }
//        if(book.getName() != null) {
//            throw new ValidatorException("name must not be null");
//        }
//        if(book.getAuthor() != null) {
//            throw new ValidatorException("author must not be null");
//        }
//        if(book.getPrice() < 0) {
//            throw new ValidatorException("price must be greater than 0");
//        }
    }
}
