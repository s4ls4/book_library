package domain.validators;

import domain.Book;

import java.util.Arrays;

public class BookValidator implements Validator<Book> {
    @Override
    public void validate(Book book) throws ValidatorException
    {

        Arrays.stream(ValidatorsBook.values()).filter(v -> v.predicate.test(book)).
                findFirst().ifPresent(s->{throw new ValidatorException("Invalid input!");});


//        if(book.getSerialNumber() == null) {
//            throw new ValidatorException("serial number must not be null");
//        }
//        if(book.getName() == null) {
//            throw new ValidatorException("name must not be null");
//        }
//        if(book.getAuthor() == null) {
//            throw new ValidatorException("author must not be null");
//        }
//        if(!book.getAuthor().matches("[a-zA-Z. ]+")) {
//            throw new ValidatorException("Author must be made out of letters!");
//        }
//        if(book.getPrice() < 0) {
//            throw new ValidatorException("price must be greater than 0");
//        }
//        if(book.getPrice() > 9999) {
//            throw new ValidatorException("price must be greater than 0");
//        }
    }
}
