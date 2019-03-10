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
        System.out.println("0. Exit");

        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    public void runConsole() {
        int cmd = menu();
        while(cmd > 0) {
            if(cmd == 1) {
                this.printAllBooks();
            }
            if(cmd == 2) {
                this.addBooks();
            }
            cmd = menu();
        }
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
