import domain.Book;
import domain.validators.BookValidator;
import domain.validators.Validator;
import repository.InMemoryRepository;
import repository.Repository;
import service.BookService;
import ui.Console;

public class main {
    public static void main(String args[]) {
        //in-memory repo
        Validator<Book> studentValidator = new BookValidator();
        Repository<Long, Book> studentRepository = new InMemoryRepository<>(studentValidator);
        BookService studentService = new BookService(studentRepository);
        Console console = new Console(studentService);
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