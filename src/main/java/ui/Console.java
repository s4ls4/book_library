package ui;

import domain.Book;
import domain.Client;
import domain.Purchase;
import domain.validators.ValidatorException;
import service.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Author: Stefi Nicoara
 */
public class Console {
    private BookService bookService;
    private ClientService clientService;
    private PurchaseService purchaseService;
    private XMLBookService XMLBookService;
    private XMLClientService XMLClientService;


    public Console(BookService bookService, ClientService clientService, PurchaseService purchaseService, XMLBookService XMLBookService, XMLClientService XMLClientService) {

        this.bookService = bookService;
        this.clientService = clientService;
        this.purchaseService = purchaseService;
        this.XMLBookService = XMLBookService;
        this.XMLClientService = XMLClientService;
    }

    private int menuFormat() {
        System.out.println("___________________________");
        System.out.println(" ");
        System.out.println("  B O O K   L I B R A R Y");
        System.out.println("___________________________");
        System.out.println("1. File");
        System.out.println("2. XML");
        System.out.println("0. Exit");

        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    private int menu() {
        System.out.println("___________________________");
        System.out.println(" ");
        System.out.println("  B O O K   L I B R A R Y");
        System.out.println("___________________________");
        System.out.println(" ");
        System.out.println("1. Books operations");
        System.out.println("2. Clients operations");
        System.out.println("3. Buy a book");
        System.out.println("4. Filter inactive");
        System.out.println("5. Sort clients");
        System.out.println("0. Exit");

        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    private int menuBooks() {
        System.out.println("_________________________________________");
        System.out.println(" ");
        System.out.println("  B O O K   L I B R A R Y / B O O K S");
        System.out.println("_________________________________________");
        System.out.println(" ");
        System.out.println("1. Print all books");
        System.out.println("2. Add a book");
        System.out.println("3. Delete a book");
        System.out.println("4. Update a book");
        System.out.println("0. Exit");

        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    private int menuClients() {
        System.out.println("____________________________________________");
        System.out.println(" ");
        System.out.println("  B O O K   L I B R A R Y / C L I E N T S");
        System.out.println("____________________________________________");
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
    public void runConsole() {

        int format = menuFormat();
        while (format > 0) {
            if (format == 1) {
                int cmdMain = menu();
                while (cmdMain > 0) {
                    if (cmdMain == 1) {
                        int cmdBooks = menuBooks();
                        while (cmdBooks > 0) {
                            if (cmdBooks == 1) {
                                this.printBooksWithPaging();
                            }
                            if (cmdBooks == 2) {
                                this.addBooks();
                            }
                            if (cmdBooks == 3) {
                                this.deleteBooks();
                            }
                            if (cmdBooks == 4) {
                                this.updateBooks();
                            }
                            cmdBooks = menuBooks();
                        }
                    }
                    if (cmdMain == 2) {
                        int cmdClients = menuClients();
                        while (cmdClients > 0) {
                            if (cmdClients == 1) {
                                this.printClientsWithPaging();
                            }
                            if (cmdClients == 2) {
                                this.addClients();
                            }
                            if (cmdClients == 3) {
                                this.deleteClients();
                            }
                            if (cmdClients == 4) {
                                this.updateClient();
                            }
                            cmdClients = menuClients();
                        }
                    }
                    if (cmdMain == 3) {
                        this.buyBook();
                    }
                    if (cmdMain == 4) {
                        this.filterClients();
                    }
                    if (cmdMain == 5) {
                        this.sortClients();
                    }
                    cmdMain = menu();
                }
            }
            if (format == 2) {
                int cmdMain = menu();
                while (cmdMain > 0) {
                    if (cmdMain == 1) {
                        int cmdBooks = menuBooks();
                        while (cmdBooks > 0) {
                            if (cmdBooks == 1) {
                                this.printAllBooksXML();
                            }
                            if (cmdBooks == 2) {
                                this.XMLAddBooks();
                            }
                            if (cmdBooks == 3) {
                                this.XMLDeleteBooks();
                            }
                            if (cmdBooks == 4) {
                                this.updateBookXML();
                            }
                            if (cmdBooks == 5) {
                                this.updateBookXML();
                            }
                            cmdBooks = menuBooks();
                        }
                    }
                    if (cmdMain == 2) {
                        int cmdClients = menuClients();
                        while (cmdClients > 0) {
                            if (cmdClients == 1) {
                                this.printAllClientsXML();
                            }
                            if (cmdClients == 2) {
                                this.XMLAddClients();
                            }
                            if (cmdClients == 3) {
                                this.XMLDeleteClients();
                            }
                            if (cmdClients == 4) {
                                this.XMLUpdateClient();
                            }
                            cmdClients = menuClients();
                        }
                    }
                    if (cmdMain == 3) {
                        this.buyBookXML();
                    }
                    if (cmdMain == 4) {
                        this.filterClientsXML();
                    }
                    if (cmdMain == 5) {
                        this.sortClientsXML();
                    }
                    cmdMain = menu();
                }
            }
        }
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

    private void printAllBooksXML() {
        Set<Book> book = this.XMLBookService.getAllBooks();
        book.forEach((i) -> System.out.println(i.toString()));
    }

    private void printAllClientsXML() {
        Set<Client> client = this.XMLClientService.getAllClients();
        client.forEach((i) -> System.out.println(i.toString()));
    }

    /**
     * Adds a book to the repository
     */
    private void addBooks() {

        try {
            Book book = this.readBook();
            this.bookService.addBook(book);
            this.XMLBookService.addBook(book);
        } catch (Exception e) {
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

        try {
            Book book = this.readBook();
            this.XMLBookService.addBook(book);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }


    private void XMLAddClients() {
        Client client = this.readClient();

        try {
            this.XMLClientService.addClient(client);
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

    private void XMLDeleteBooks() {
        System.out.println("Book id: ");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

        try {
            Long id = Long.valueOf(bufferRead.readLine());
            this.XMLBookService.deleteBook(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void XMLDeleteClients() {
        System.out.println("Client id: ");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

        try {
            Long id = Long.valueOf(bufferRead.readLine());
            this.XMLClientService.deleteClient(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates a book from the repository
     */
    private void updateBooks() {

        try {
            Book book = this.readBook();
            this.bookService.updateBook(book);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
        }
    }

    private void updateBookXML() {
//        System.out.println("Book id: ");
//        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
//
//        try {
//            Long id = Long.valueOf(bufferRead.readLine());
//            this.XMLBookService.deleteBook(id);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        this.XMLDeleteBooks();
        this.XMLAddBooks();


//        try {
//            Book book = this.readBook();
//            this.XMLBookService.addBook(book);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
    }

    private void XMLUpdateClient() {
//        System.out.println("Client id: ");
//        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
//
//        try {
//            Long id = Long.valueOf(bufferRead.readLine());
//            this.XMLClientService.deleteClient(id);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        this.XMLDeleteClients();
        this.XMLAddClients();

//        Client client = this.readClient();
//
//        try {
//            this.XMLClientService.addClient(client);
//        } catch (ValidatorException e) {
//            System.out.println(e.getMessage());
//        }
    }

    private void buyBook() {
        Purchase purchase = this.readPurchase();
        Set<Client> clients = this.clientService.getAllClients();
        Set<Book> books = this.bookService.getAllBooks();


        buyOperation(purchase, clients, books);
    }

    private void buyOperation(Purchase purchase, Set<Client> clients, Set<Book> books) {
        try {
            final int[] price = new int[1];
            this.purchaseService.addPurchase(purchase);
            books.forEach(i -> {
                if (i.getId().equals(purchase.getIdBook())) {
                    price[0] = i.getPrice();
                }
            });

            clients.forEach((i) -> {
                if (i.getId().equals(purchase.getIdClient())) {
                    i.setSpent(i.getSpent() + price[0]);
                }
            });

        } catch (ValidatorException e) {
            System.out.println(e);
        }
    }

    private void buyBookXML() {
        Purchase purchase = this.readPurchase();
        Set<Client> clients = this.XMLClientService.getAllClients();
        Set<Book> books = this.XMLBookService.getAllBooks();


        buyOperation(purchase, clients, books);
    }

    private void filterClients() {
        Set<Client> clients = this.clientService.getAllClients();
        Stream<Client> inactive = clients.stream().filter(c -> c.getSpent() == 0);
        inactive.forEach((i) -> System.out.println(i.toString()));
    }

    private void filterClientsXML() {
        Set<Client> clients = this.XMLClientService.getAllClients();
        Stream<Client> inactive = clients.stream().filter(c -> c.getSpent() == 0);
        inactive.forEach((i) -> System.out.println(i.toString()));
    }

    private void sortClients() {
        Set<Client> clients = this.clientService.getAllClients();

        clients.stream().sorted(Comparator.comparing(Client::getSpent))
                .forEach(c -> System.out.println(c.toString()));
    }

    private void sortClientsXML() {
        Set<Client> clients = this.XMLClientService.getAllClients();

        clients.stream().sorted(Comparator.comparing(Client::getSpent))
                .forEach(c -> System.out.println(c.toString()));
    }

    /**
     * Reads a book from the console
     *
     * @return the book object
     */
    private Book readBook() throws Exception {
        System.out.println("Read book {id, serialNumber, name, author, price}");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        Long id = Long.valueOf(bufferRead.readLine());
        String serialNumber = bufferRead.readLine();
        String name = bufferRead.readLine();
        String author = bufferRead.readLine();
        int price = Integer.parseInt(bufferRead.readLine());
        Book book = new Book(serialNumber, name, author, price);
        book.setId(id);
        return book;
    }

    /**
     * Reads a client from the console
     *
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
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
            return null;
        }
    }

    private void printBooksWithPaging() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter page size: ");
        int size = scanner.nextInt();
        bookService.setPageSize(size);

        System.out.println("enter 'n' - for next; 'x' - for exit: ");

        while (true) {
            String cmd = scanner.next();
            if (cmd.equals("x")) {
                System.out.println("exit");
                break;
            } else if (cmd.equals("n")) {
                Set<Book> books = bookService.getNextBook();
                if (books.size() == 0) {
                    System.out.println("no more students");
                    break;
                }
                books.forEach(System.out::println);
            } else {
                System.out.println("Invalid input!");
            }
        }
    }
    private void printClientsWithPaging() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter page size: ");
        int size = scanner.nextInt();
        clientService.setPageSize(size);

        System.out.println("enter 'n' - for next; 'x' - for exit: ");

        while (true) {
            String cmd = scanner.next();
            if (cmd.equals("x")) {
                System.out.println("exit");
                break;
            } else if (cmd.equals("n")) {
                Set<Client> clients = clientService.getNextClient();
                if (clients.size() == 0) {
                    System.out.println("no more students");
                    break;
                }
                clients.forEach(System.out::println);
            } else {
                System.out.println("Invalid input!");
            }
        }
    }
}
