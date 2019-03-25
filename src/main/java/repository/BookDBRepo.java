package repository;

import domain.Book;
import domain.validators.Validator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDBRepo extends InMemoryRepository<Long, Book> {

    private static final String URL = "jdbc:postgresql://localhost:5432/LibraryApp";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "98blionlion";

    public BookDBRepo(Validator<Book> validator){
        super(validator);
    }


    public Iterable<Book> findAll(){

        List<Book> books = new ArrayList<>();
        String sql ="SELECT * FROM books";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("bid");
                String serialNumber = resultSet.getString("bserialNumber");
                String name = resultSet.getString("bname");
                String author = resultSet.getString("author");
                int price = resultSet.getInt("price");

                Book newBook = new Book(serialNumber, name,author, price);
                newBook.setId(id);
                books.add(newBook);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }


    public Optional<Book> save(Optional<Book> book) {

        String sql = "insert into books(bname,\"bserialNumber\",author,price,bid) values (?,?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, book.get().getName());
            statement.setString(2, book.get().getSerialNumber());
            statement.setString(3, book.get().getAuthor());
            statement.setInt(4, book.get().getPrice());
            statement.setLong(5, book.get().getId());

            statement.executeUpdate();
            return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(book.get());

    }

    public Optional<Book> delete(Optional<Long> id) {
        String sql = "DELETE FROM books WHERE bid=?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id.get());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Book> update(Optional<Book> book) {
        String sql = "UPDATE books SET \"bserialNumber\"=?, bname=?, author=?, price=? where bid=?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, book.get().getSerialNumber());
            statement.setString(2, book.get().getName());
            statement.setString(3, book.get().getAuthor());
            statement.setInt(4, book.get().getPrice());
            statement.setLong(5, book.get().getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


}
