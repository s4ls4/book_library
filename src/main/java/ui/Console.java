package ui;

import domain.Book;
import domain.Client;
import domain.Purchase;
import domain.validators.ValidatorException;
import service.BookService;
import service.ClientService;
import service.XMLBookService;
import service.PurchaseService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Author: Stefi Nicoara
 */
public class Console {
    private BookService bookService;
    private ClientService clientService;
    private PurchaseService purchaseService;
    private XMLBookService XMLBookService;


    public Console(BookService bookService, ClientService clientService, PurchaseService purchaseService, XMLBookService XMLBookService) {

        this.bookService = bookService;
        this.clientService = clientService;
        this.purchaseService = purchaseService;
        this.XMLBookService = XMLBookService; }

    public int menu() {
        System.out.println("___________________________");
        System.out.println(" ");
        System.out.println("  B O O K   L I B R A R Y");
        System.out.println("___________________________");
        System.out.println(" ");
        System.out.println("1. Books operations");
        System.out.println("2. Clients operations");
        System.out.println("3. Buy a book");
        System.out.println("4. Filter");
        System.out.println("0. Exit");

        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    public int menuBooks() {
        System.out.println("___________________________");
        System.out.println(" ");
        System.out.println("  B O O K   L I B R A R Y");
        System.out.println("___________________________");
        System.out.println(" ");
        System.out.println("1. Print all books");
        System.out.println("2. Add a book");
        System.out.println("3. Delete a book");
        System.out.println("4. Update a book");
        System.out.println("5. XML Add Book");
        System.out.println("0. Exit");

        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    public int menuClients() {
        System.out.println("___________________________");
        System.out.println(" ");
        System.out.println("  B O O K   L I B R A R Y");
        System.out.println("___________________________");
        System.out.println(" ");
        System.out.println("1. Print all clients");
        System.out.println("2. Add a client");
        System.out.println("3. Delete a client");
        System.out.println("4. Update a client");
        System.out.println("0. Exit");


        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }


    /**
     * Starts the application
     */
    public void runConsole() throws Exception {
        //initialize();
        int cmdMain = menu();
        while (cmdMain > 0) {
            if (cmdMain == 1) {
                int cmdBooks = menuBooks();
                while (cmdBooks > 0) {
                    if(cmdBooks == 1) {
                        this.printAllBooks();
                    }
                    if(cmdBooks == 2) {
                        this.addBooks();
                    }
                    if(cmdBooks == 3) {
                        this.deleteBooks();
                    }
                    if(cmdBooks == 4) {
                        this.updateBooks();
                    }
                    if(cmdBooks == 5) {
                        this.printAllXML();
                    }
                    cmdBooks = menuBooks();
                }
            }
            if (cmdMain == 2) {
                int cmdClients = menuClients();
                while(cmdClients > 0) {
                    if(cmdClients == 1) {
                        this.printAllClients();
                    }
                    if(cmdClients == 2) {
                        this.addClients();
                    }
                    if(cmdClients == 3) {
                        this.deleteClients();
                    }
                    if(cmdClients == 4) {
                        this.updateClient();
                    }
                    cmdClients = menuClients();
                }
            }
            if (cmdMain == 3) {
                this.buyBook();
            }
            cmdMain = menu();
        }
    }

    /**
     * Initializes the application with some valid entities
     */
    private void initialize() {
        Book book1 = new Book("2AB3221", "Harry Potter 1", "J.K. Rowling", 32);
        book1.setId(1L);
        Book book2 = new Book("433qwdE", "Lolita", "Vladimir Nabokov", 27);
        book2.setId(2L);
        Book book3 = new Book("wdaw221", "Jane Eyre", "Charlotte Brontë", 40);
        book3.setId(3L);
        Book book4 = new Book("2e21dT1", "Wuthering Heights", "Emily Brontë", 32);
        book4.setId(4L);
        Book book5 = new Book("7654wsd", "The Great Gatsby", "F. Scott Fitzgerald", 30);
        book5.setId(5L);
        this.bookService.addBook(book1);
        this.bookService.addBook(book2);
        this.bookService.addBook(book3);
        this.bookService.addBook(book4);
        this.bookService.addBook(book5);
    }

    /**
     * Prints all books from the repository
     */
    private void printAllBooks() {
        Set<Book> books = this.bookService.getAllBooks();
        books.forEach((i) -> System.out.println(i.toString()));
    }


    /**
     * Prints all clients from the repository
     */
    private void printAllClients() {
        Set<Client> client = this.clientService.getAllClients();
        client.forEach((i) -> System.out.println(i.toString()));
    }

    private void printAllXML() {
        Set<Book> client = this.XMLBookService.printAllBooks();
        client.forEach((i) -> System.out.println(i.toString()));
    }

    /**
     * Adds a book to the repository
     */
    private void addBooks() {
        Book book = this.readBook();

        try {
            this.bookService.addBook(book);
        } catch (ValidatorException e) {
            System.out.println(e);
        }
    }

    /**
     * Adds a client to the repository
     */
    private void addClients() {
        Client client = this.readClient();
        try {
            this.clientService.addClient(client);
        } catch (ValidatorException e) {
            System.out.println(e);
        }
    }

    private void XMLAddBooks() {
        Book book = this.readBook();

        try {
            this.XMLBookService.addBook(book);
        } catch (ValidatorException e) {
            System.out.println(e);
        }
    }

    /**
     * Deletes a book from the repository
     */
    private void deleteBooks() {
        System.out.println("Book id: ");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

        try {
            Long id = Long.valueOf(bufferRead.readLine());
            this.bookService.deleteBook(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a client from the repository
     */
    private void deleteClients() {
        System.out.println("Client id: ");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

        try {
            Long id = Long.valueOf(bufferRead.readLine());
            this.clientService.deleteClient(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates a book from the repository
     */
    private void updateBooks() {
        Book book = this.readBook();

        try {
            this.bookService.updateBook(book);
        } catch (ValidatorException e) {
            System.out.println(e);
        }
    }

    /**
     * Update a client from the repository
     */
    private void updateClient() {
        Client client = this.readClient();

        try {
            this.clientService.updateClient(client);
        } catch (ValidatorException e) {
            System.out.println(e);
        }
    }

    private void buyBook() throws Exception {
        Purchase purchase = this.readPurchase();

        try {
            this.purchaseService.addPurchase(purchase);
        } catch (ValidatorException e) {
            System.out.println(e);
        }
    }

    /**
     * Reads a book from the console
     * @return the book object
     */
    private Book readBook() {
        System.out.println("Read book {id, serialNumber, name, author, price}");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

        try {
            Long id = Long.valueOf(bufferRead.readLine());
            String serialNumber = bufferRead.readLine();
            String name = bufferRead.readLine();
            String author = bufferRead.readLine();
            int price = Integer.parseInt(bufferRead.readLine());
            Book book = new Book(serialNumber, name, author, price);
            book.setId(id);
            return book;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Reads a client from the console
     * @return the client as the object
     */
    private Client readClient() {
        System.out.println("Read client {id, serialNumber, name, spent}");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            Long idd;
            idd = Long.valueOf(bufferedReader.readLine());
            String serialNumber = bufferedReader.readLine();
            String name = bufferedReader.readLine();
            int spent = Integer.parseInt(bufferedReader.readLine());
            Client client = new Client(serialNumber, name, spent);
            client.setId(idd);
            return client;

        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    private Purchase readPurchase() {
        System.out.println("Read purchase {id, ClientId, BookId}");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            Long id;
            id = Long.valueOf(bufferedReader.readLine());
            Long idClient;
            idClient = Long.valueOf(bufferedReader.readLine());
            Long idBook;
            idBook = Long.valueOf(bufferedReader.readLine());

            Purchase p = new Purchase(idClient, idBook);
            p.setId(id);
            return p;

        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }
}
