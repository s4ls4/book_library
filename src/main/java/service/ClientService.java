package service;

import domain.Book;
import domain.Client;
import domain.validators.ValidatorException;
import repository.Repository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 *  author: Stefi Nicoara
 */
public class ClientService {
    private Repository<Long, Client> repository;

    public ClientService(Repository<Long, Client> repository) {
        this.repository = repository;
    }

    public void addClient(Client client) throws ValidatorException {
        repository.save(client);
    }

    public Set<Client> getAllBooks() {
        Iterable<Client> clients = this.repository.findAll();
        return (Set) StreamSupport.stream(clients.spliterator(), false).collect(Collectors.toSet());
    }
}
