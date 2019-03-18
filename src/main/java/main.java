import domain.Book;
import domain.Client;
import domain.Purchase;
import domain.validators.BookValidator;
import domain.validators.ClientValidator;
import domain.validators.PurchaseValidator;
import domain.validators.Validator;
import repository.*;
import service.*;
import ui.Console;

import java.io.File;
import java.io.IOException;

public class main {
    public static void main(String args[]) throws Exception {
        //in-memory repo
//        Validator<Book> bookValidator = new BookValidator();
//        Repository<Long, Book> bookRepository = new InMemoryRepository<>(bookValidator);
//        BookService bookService = new BookService(bookRepository);
//
//        Validator<Client> clientValidator = new ClientValidator();
//        Repository<Long, Client> clientRepository = new InMemoryRepository<>(clientValidator);
//        ClientService clientService = new ClientService(clientRepository);
//
//        Console console = new Console(bookService, clientService);
//        console.runConsole();

      //file repo
//       try {
//           System.out.println(new File(".").getCanonicalPath());
//       } catch (IOException e) {
//           System.out.println(e);
//       }
       //in file repo
//     Validator<Book> studentValidator = new BookValidator();
//     Repository<Long, Book> bookRepository = new BookFileRepository(studentValidator, "C:\\Users\\Birhan\\Desktop\\Mpp proiecte\\Library_app\\src\\main\\resources\\Books");
//     BookService bookService = new BookService(bookRepository);
//
//    Validator<Client> clientValidator = new ClientValidator();
//    Repository<Long, Client> clientRepository = new ClientFileRepository(clientValidator, "C:\\Users\\Birhan\\Desktop\\Mpp proiecte\\Library_app\\src\\main\\resources\\Clients");
//    ClientService clientService = new ClientService(clientRepository);
//
//     Console console = new Console(bookService, clientService);
//     console.runConsole();


        try {
            System.out.println(new File(".").getCanonicalPath());
        } catch (IOException e) {
            System.out.println(e);
        }

        Validator<Book> bookValidator = new BookValidator();
        Repository<Long, Book> bookRepository = new BookFileRepository(bookValidator, "C:\\Users\\Birhan\\Desktop\\Mpp proiecte\\Library_app\\src\\main\\resources\\Books");
        BookService bookService = new BookService(bookRepository);

        Validator<Client> clientValidator = new ClientValidator();
        Repository<Long, Client> clientRepository = new ClientFileRepository(clientValidator, "C:\\Users\\Birhan\\Desktop\\Mpp proiecte\\Library_app\\src\\main\\resources\\Clients");
        ClientService clientService = new ClientService(clientRepository);

        Validator<Purchase> purchaseValidator = new PurchaseValidator();
        Repository<Long, Purchase> purchaseRepository = new InMemoryRepository<>(purchaseValidator);
        PurchaseService purchaseService = new PurchaseService(purchaseRepository);

        Validator<Book> bookValidatorXML = new BookValidator();
        Repository<Long, Book> XMLBookRepo = new XMLRepositoryBook(bookValidatorXML, "C:\\Users\\Birhan\\Desktop\\Mpp proiecte\\Library_app\\src\\main\\resources\\BookXML");
        XMLBookService XMLBookService = new XMLBookService(XMLBookRepo);

        Validator<Client> clientValidatorXML = new ClientValidator();
        Repository<Long, Client> XMLClientRepo = new XMLRepositoryClient(clientValidatorXML, "C:\\Users\\Birhan\\Desktop\\Mpp proiecte\\Library_app\\src\\main\\resources\\ClientXML");
        XMLClientService XMLClientService = new XMLClientService(XMLClientRepo);


        Console console = new Console(bookService, clientService, purchaseService,XMLBookService, XMLClientService);
        console.runConsole();
        System.out.println("Bye World!");
    }
}