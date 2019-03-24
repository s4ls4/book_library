package repository;

import domain.Client;
import domain.validators.Validator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDBRepo extends InMemoryRepository<Long, Client> {

    private static final String URL = "jdbc:postgresql://localhost:5432/LibraryAppMPP";
    private static final String USERNAME = System.getProperty("username");
    private static final String PASSWORD = System.getProperty("password");

    public ClientDBRepo(Validator<Client> validator){
        super(validator);
    }


    public Iterable<Client> findAll(){

        List<Client> clients = new ArrayList<>();
        String sql ="SELECT * FROM Clients";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("cid");
                String serialNumber = resultSet.getString("cserialNumber");
                String name = resultSet.getString("cname");
                int spent = resultSet.getInt("spent");

                Client newClient = new Client(serialNumber, name, spent);
                newClient.setId(id);
                clients.add(newClient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }


    public Optional<Client> save(Client client) {
        String sql = "INSERT INTO Clients(cid,cserialNumber,cname,spent) values (?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, client.getId());
            statement.setString(2, client.getSerialNumber());
            statement.setString(3, client.getName());
            statement.setInt(4, client.getSpent());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Client> delete(Long id) {
        String sql = "DELETE FROM Clients WHERE cid=?";
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

    public Optional<Client> update(Client client) {
        String sql = "UPDATE Client SET cserialNumber=?, cname=?, spent=? where cid=?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME,
                PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, client.getSerialNumber());
            statement.setString(2, client.getName());
            statement.setInt(3, client.getSpent());
            statement.setLong(4, client.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


}
