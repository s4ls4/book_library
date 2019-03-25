package service;
import domain.Book;

import repository.Paging.Page;
import repository.Paging.PageRequest;
import repository.Paging.Pageable;
import repository.Paging.PagingRepository;
import repository.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class XMLBookService {
    private PagingRepository<Long, Book> xmlRepo;

    private int page = 0;
    private int size = 1;

    public XMLBookService(PagingRepository<Long, Book> xmlRepo) {
        this.xmlRepo = xmlRepo;
    }

    //COPY THIS TO THE OTHER SERVICES
    public void setPageSize(int size) {
        this.size = size;
        this.page = 0;
    }

    //COPY THIS TO THE OTHER SERVICES
    public Set<Book> getNextBook() {
        Pageable pageable = PageRequest.of(size, page);
        try{
            Page<Book> bookPage = xmlRepo.findAll(pageable);
            page = bookPage.nextPageable().getPageNumber();
            return bookPage.getContent().collect(Collectors.toSet());
        }catch (IndexOutOfBoundsException ex) {
            page = 0;
            return new HashSet<>();
        }
    }

    public void addBook(Book book) {

        xmlRepo.save(Optional.ofNullable(book));
    }


    public Set getAllBooks() {
        Iterable<Book> books = this.xmlRepo.findAll();
        return (Set) StreamSupport.stream(books.spliterator(), false).collect(Collectors.toSet());
    }

    public void deleteBook(Long ID) {
        xmlRepo.delete(Optional.ofNullable(ID));
    }
}
