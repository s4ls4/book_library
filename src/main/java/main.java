import domain.Book;
import domain.Client;
import domain.Purchase;
import domain.validators.BookValidator;
import domain.validators.ClientValidator;
import domain.validators.PurchaseValidator;
import domain.validators.Validator;
import repository.*;
import repository.Paging.PagingRepository;
import service.*;
import ui.Console;

import java.io.File;
import java.io.IOException;

public class main {
    public static void main(String args[]) throws Exception {


        try {
            System.out.println(new File(".").getCanonicalPath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        Validator<Book> bookValidator = new BookValidator();
        Validator<Client> clientValidator= new ClientValidator();
        Validator<Purchase> purchaseValidator = new PurchaseValidator();






        PagingRepository<Long, Book> XMLBookRepo = new XMLRepositoryBook(bookValidator, "C:\\Faculty\\MPP\\LibraryAppRFC\\book_library\\src\\main\\resources\\BookXML");
        //PagingRepository<Long, Book> XMLBookRepo = new XMLRepositoryBook(bookValidator, "C:\\Users\\Birhan\\Desktop\\AN 2 Sem 2\\MPP\\Mpp proiecte\\Library_app\\src\\main\\resources\\BookXML");
        XMLBookService XMLBookService = new XMLBookService(XMLBookRepo);


        PagingRepository<Long, Client> XMLClientRepo = new XMLRepositoryClient(clientValidator, "C:\\Faculty\\MPP\\LibraryAppRFC\\book_library\\src\\main\\resources\\ClientXML");
        //PagingRepository<Long, Client> XMLClientRepo = new XMLRepositoryClient(clientValidator, "C:\\Users\\Birhan\\Desktop\\AN 2 Sem 2\\MPP\\Mpp proiecte\\Library_app\\src\\main\\resources\\ClientXML");
        XMLClientService XMLClientService = new XMLClientService(XMLClientRepo);



        Console console = new Console(XMLBookService, XMLClientService);
        console.runConsole();
        System.out.println("Bye World!");
    }
}