package ui;

import domain.Book;
import domain.Client;
import domain.validators.ValidatorException;
import service.BookService;
import service.ClientService;
import service.XMLBookService;

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
    private XMLBookService XMLB;

    public Console(BookService bookService, ClientService clientService, XMLBookService XMLB) {

        this.bookService = bookService;
        this.clientService = clientService;
        this.XMLB = XMLB;
    }

    public int menu(){
        System.out.println("___________________________");
        System.out.println(" ");
        System.out.println("  B O O K   L I B R A R Y");
        System.out.println("___________________________");
        System.out.println(" ");
        System.out.println("1. Print all books");
        System.out.println("2. Add a book");
        System.out.println("3. Delete a book");
        System.out.println("4. Print all clients");
        System.out.println("5. Add a client");
        System.out.println("6. Delete a client");
        System.out.println("0. Exit");

        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    /**
     * Starts the application
     */
    public void runConsole() {
        //initialize();
        int cmd = menu();
        while(cmd > 0) {
            if(cmd == 1) {
                this.printAllBooks();
            }
            if(cmd == 2) {
                this.addBooks();
            }
            if(cmd == 3) {
                this.deleteBooks();
            }
            if(cmd == 4) {
                this.printAllClients();
            }
            if(cmd == 5) {
                this.addClients();
            }
            if(cmd == 6) {
                this.deleteClients();
            }
            cmd = menu();
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
        books.forEach( (i)-> System.out.println(i.toString()));
    }


    private void printAllClients() {
        Set<Client> client = this.clientService.getAllClients();
        client.forEach( (i)-> System.out.println(i.toString()));
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

    private void addClients() {
        Client client = this.readClient();
        try{
            this.clientService.addClient(client);
        } catch (ValidatorException e) {
            System.out.println(e);
        }
    }

    /**
     *  Deletes a book from the repository
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

    private Client readClient(){
        System.out.println("Read client {id, serialNumber, name, spent}");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try{
            Long idd;
            idd = Long.valueOf(bufferedReader.readLine());
            String serialNumber = bufferedReader.readLine();
            String name = bufferedReader.readLine();
            int spent = Integer.parseInt(bufferedReader.readLine());
            Client client = new Client(serialNumber,name,spent);
            client.setId(idd);
            return client;

        } catch (IOException e) {
            System.out.println(e);
            return null;
        }

    }
}
