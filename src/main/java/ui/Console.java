package ui;

import domain.Book;
import domain.validators.ValidatorException;
import service.BookService;

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

    public Console(BookService studentService) {
        this.bookService = studentService;
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
        System.out.println("0. Exit");

        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    public void runConsole() {
        initialize();
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
            cmd = menu();
        }
    }

    private void initialize() {
        Book book1 = new Book("2AB3221", "Harry Potter 1", "J.K. Rowling", 32);
        book1.setId(1L);
        Book book2 = new Book("433qwdE", "Harry Potter 2", "J.K. Rowling", 32);
        book2.setId(2L);
        Book book3 = new Book("wdaw221", "Harry Potter 3", "J.K. Rowling", 32);
        book3.setId(3L);
        Book book4 = new Book("2e21dT1", "Harry Potter 4", "J.K. Rowling", 32);
        book4.setId(4L);
        Book book5 = new Book("7654wsd", "Harry Potter 5", "J.K. Rowling", 32);
        book5.setId(5L);
        this.bookService.addBook(book1);
        this.bookService.addBook(book2);
        this.bookService.addBook(book3);
        this.bookService.addBook(book4);
        this.bookService.addBook(book5);
    }

    private void printAllBooks() {
        Set<Book> books = this.bookService.getAllBooks();
        books.forEach( (i)-> System.out.println(i.toString()));
    }

    private void addBooks() {
        Book book = this.readBook();

        try {
            this.bookService.addBook(book);
        } catch (ValidatorException var3) {
            var3.printStackTrace();
        }
    }

    private void deleteBooks() {
        System.out.println("Book id: ");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));

        try {
            Long id = Long.valueOf(bufferRead.readLine());
            this.bookService.deleteBook(id);
        } catch (IOException var7) {
            var7.printStackTrace();
        }
    }

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
        } catch (IOException var7) {
            var7.printStackTrace();
            return null;
        }
    }
}
