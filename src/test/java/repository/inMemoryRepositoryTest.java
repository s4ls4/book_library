//package repository;

import domain.Book;

import domain.validators.BookValidator;
import domain.validators.Validator;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//public class inMemoryRepositoryTest {
//
//    private Validator<Book> bookValidator = new BookValidator();
//
//    @Test
//    public void findOneTest() {
//        Repository<Long, Book> repo = new InMemoryRepository<>(bookValidator);
//        Book book = new Book("test1", "test", " test", 9);
//        book.setId(1L);
//
//        repo.save(book);
//
//        assertTrue(repo.findOne(book.getId()).isPresent());
//    }
//
//    @Test
//    public void findAllTest() {
//        Repository<Long, Book> repo = new InMemoryRepository<>(bookValidator);
//        Book book1 = new Book("test1", "test", "test", 2);
//        book1.setId(1L);
//        Book book2 = new Book("test2", "test", "test", 6);
//        book1.setId(1L);
//        book2.setId(2L);
//
//        repo.save(book1);
//        repo.save(book2);
//
//        assertEquals(StreamSupport.stream(repo.findAll().spliterator(), false).count(),2);
//    }
//
//    @Test
//    public void saveTest() {
//        Repository<Long, Book> repo = new InMemoryRepository<>(bookValidator);
//        Book book = new Book("test1", "test", "test", 6);
//        book.setId(1L);
//
//        repo.save(book);
//        assertEquals(StreamSupport.stream(repo.findAll().spliterator(), false).count(),1);
//    }
//
//    @Test
//    public void deleteTest() {
//        Repository<Long, Book> studentIRepository = new InMemoryRepository<>(bookValidator);
//        Book book = new Book("test1", "test", "test",6);
//        book.setId(1L);
//
//        studentIRepository.save(book);
//        assertEquals(StreamSupport.stream(studentIRepository.findAll().spliterator(), false).count(),1);
//
//        studentIRepository.delete(1L);
//        assertEquals(StreamSupport.stream(studentIRepository.findAll().spliterator(), false).count(),0);
//    }
//}
