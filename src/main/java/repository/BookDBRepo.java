package repository;

import domain.Book;
import domain.validators.Validator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDBRepo extends InMemoryRepository<Long, Book> {

    private static final String URL = "jdbc:postgresql://localhost:5432/LibraryApp";
    private static final String USERNAME = System.getProperty("username");
    private static final String PASSWORD = System.getProperty("password");

    public BookDBRepo(Validator<Book> validator){
        super(validator);
    }


    public Iterable<Book> findAll(){

        List<Book> books = new ArrayList<>();
        String sql ="SELECT * FROM Books";

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

    public Optional<Book> save(Book book) {
        String sql = "INSERT INTO Books(bid,bserialNumber,bname,author,price) values (?,?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, book.getId());
            statement.setString(2, book.getSerialNumber());
            statement.setString(3, book.getName());
            statement.setString(4, book.getAuthor());
            statement.setInt(5, book.getPrice());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Book> delete(Long id) {
        String sql = "DELETE FROM Books WHERE bid=?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Book> update(Book book) {
        String sql = "UPDATE Book SET bserialNumber=?, bname=?, author=?, price=? where bid=?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, book.getSerialNumber());
            statement.setString(2, book.getName());
            statement.setString(3, book.getAuthor());
            statement.setInt(4, book.getPrice());
            statement.setLong(5, book.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


}
