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


        try {
            System.out.println(new File(".").getCanonicalPath());
        } catch (IOException e) {
            System.out.println(e);
        }

        Validator<Book> bookValidator= new BookValidator();
        Validator<Client> clientValidator= new ClientValidator();
        Validator<Purchase> purchaseValidator = new PurchaseValidator();


        Repository<Long, Book> FileBookRepo = new BookFileRepository(bookValidator, "C:\\Users\\Birhan\\Desktop\\Mpp proiecte\\Library_app\\src\\main\\resources\\Books");
        BookService bookServiceFile = new BookService(FileBookRepo);

        Repository<Long, Client> FileClientRepo = new ClientFileRepository(clientValidator, "C:\\Users\\Birhan\\Desktop\\Mpp proiecte\\Library_app\\src\\main\\resources\\Clients");
        ClientService clientServiceFile = new ClientService(FileClientRepo);

        Repository<Long, Purchase> purchaseRepository = new InMemoryRepository<>(purchaseValidator);
        PurchaseService purchaseService = new PurchaseService(purchaseRepository);

        Repository<Long, Book> XMLBookRepo = new XMLRepositoryBook(bookValidator, "C:\\Users\\Birhan\\Desktop\\Mpp proiecte\\Library_app\\src\\main\\resources\\BookXML");
        XMLBookService XMLBookService = new XMLBookService(XMLBookRepo);

        Repository<Long, Client> XMLClientRepo = new XMLRepositoryClient(clientValidator, "C:\\Users\\Birhan\\Desktop\\Mpp proiecte\\Library_app\\src\\main\\resources\\ClientXML");
        XMLClientService XMLClientService = new XMLClientService(XMLClientRepo);

        Repository<Long, Book> DBBookRepo = new BookDBRepo(bookValidator);
        DBBookService DBBookService = new DBBookService(DBBookRepo);

        Repository<Long, Client> DBClientRepo = new ClientDBRepo(clientValidator);
        DBClientService DBClientService = new DBClientService(DBClientRepo);

        Console console = new Console(bookServiceFile, clientServiceFile, purchaseService,XMLBookService, XMLClientService, DBBookService, DBClientService);
        console.runConsole();
        System.out.println("Bye World!");
    }
}