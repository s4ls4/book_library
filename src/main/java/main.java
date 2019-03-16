import domain.Book;
import domain.Client;
import domain.validators.BookValidator;
import domain.validators.ClientValidator;
import domain.validators.Validator;
import repository.BookFileRepository;
import repository.ClientFileRepository;
import repository.InMemoryRepository;
import repository.Repository;
import service.BookService;
import service.ClientService;
import ui.Console;

import java.io.File;
import java.io.IOException;

public class main {
    public static void main(String args[]) {
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
       try {
           System.out.println(new File(".").getCanonicalPath());
       } catch (IOException e) {
           System.out.println(e);
       }
       //in file repo
     Validator<Book> studentValidator = new BookValidator();
     Repository<Long, Book> bookRepository = new BookFileRepository(studentValidator, "C:\\Users\\Birhan\\Desktop\\Mpp proiecte\\Library_app\\src\\main\\resources\\Books");
     BookService bookService = new BookService(bookRepository);

    Validator<Client> clientValidator = new ClientValidator();
    Repository<Long, Client> clientRepository = new ClientFileRepository(clientValidator, "C:\\Users\\Birhan\\Desktop\\Mpp proiecte\\Library_app\\src\\main\\resources\\Clients");
    ClientService clientService = new ClientService(clientRepository);

     Console console = new Console(bookService, clientService);
     console.runConsole();

        System.out.println("Bye World!");
    }
}