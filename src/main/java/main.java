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

        Validator<Book> bookValidatorFile = new BookValidator();
        Repository<Long, Book> FileBookRepo = new BookFileRepository(bookValidatorFile, "C:\\Users\\Birhan\\Desktop\\Mpp proiecte\\Library_app\\src\\main\\resources\\Books");
        BookService bookServiceFile = new BookService(FileBookRepo);

        Validator<Client> clientValidatorFile = new ClientValidator();
        Repository<Long, Client> FileClientRepo = new ClientFileRepository(clientValidatorFile, "C:\\Users\\Birhan\\Desktop\\Mpp proiecte\\Library_app\\src\\main\\resources\\Clients");
        ClientService clientServiceFile = new ClientService(FileClientRepo);



        Validator<Purchase> purchaseValidator = new PurchaseValidator();
        Repository<Long, Purchase> purchaseRepository = new InMemoryRepository<>(purchaseValidator);
        PurchaseService purchaseService = new PurchaseService(purchaseRepository);

        Validator<Book> bookValidatorXML = new BookValidator();
        Repository<Long, Book> XMLBookRepo = new XMLRepositoryBook(bookValidatorXML, "C:\\Users\\Birhan\\Desktop\\Mpp proiecte\\Library_app\\src\\main\\resources\\BookXML");
        XMLBookService XMLBookService = new XMLBookService(XMLBookRepo);

        Validator<Client> clientValidatorXML = new ClientValidator();
        Repository<Long, Client> XMLClientRepo = new XMLRepositoryClient(clientValidatorXML, "C:\\Users\\Birhan\\Desktop\\Mpp proiecte\\Library_app\\src\\main\\resources\\ClientXML");
        XMLClientService XMLClientService = new XMLClientService(XMLClientRepo);


        Console console = new Console(bookServiceFile, clientServiceFile, purchaseService,XMLBookService, XMLClientService);
        console.runConsole();
        System.out.println("Bye World!");
    }
}