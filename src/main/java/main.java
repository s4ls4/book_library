import domain.Book;
import domain.Client;
import domain.Purchase;
import domain.validators.BookValidator;
import domain.validators.ClientValidator;
import domain.validators.PurchaseValidator;
import domain.validators.Validator;
import repository.InMemoryRepository;
import repository.Repository;
import service.BookService;
import service.ClientService;
import service.PurchaseService;
import ui.Console;

public class main {
    public static void main(String args[]) {
        //in-memory repo
        Validator<Book> bookValidator = new BookValidator();
        Repository<Long, Book> bookRepository = new InMemoryRepository<>(bookValidator);
        BookService bookService = new BookService(bookRepository);

        Validator<Client> clientValidator = new ClientValidator();
        Repository<Long, Client> clientRepository = new InMemoryRepository<>(clientValidator);
        ClientService clientService = new ClientService(clientRepository);

        Validator<Purchase> purchaseValidator = new PurchaseValidator();
        Repository<Long, Purchase> purchaseRepository = new InMemoryRepository<>(purchaseValidator);
        PurchaseService purchaseService = new PurchaseService(purchaseRepository);



        Console console = new Console(bookService, clientService, purchaseService);
        console.runConsole();
//
//        //file repo
////        try {
////            System.out.println(new File(".").getCanonicalPath());
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////        //in file repo
//        Validator<Student> studentValidator = new StudentValidator();
//        Repository<Long, Student> studentRepository = new StudentFileRepository(studentValidator, "./data/students");
//        StudentService studentService = new StudentService(studentRepository);
//        Console console = new Console(studentService);
//        console.runConsole();

        System.out.println("Bye World!");
    }
}