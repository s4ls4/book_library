package service;

import domain.Client;
import domain.validators.ValidatorException;
import repository.Repository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * author: Stefi Nicoara
 */
public class ClientService {
    private Repository<Long, Client> repository;

    /**
     * constructor for the client service
     * @param repository - the repository
     */
    public ClientService(Repository<Long, Client> repository) {
        this.repository = repository;
    }

    /**
     * Function for adding a client
     * @param client the client object
     * @throws ValidatorException
     */
    public void addClient(Client client) throws ValidatorException {
        repository.save(client);
    }

    /**
     * Function for accessing all the entities
     * @return a stream with all the objects
     */
    public Set getAllBooks() {
        Iterable<Client> clients = this.repository.findAll();
        return (Set) StreamSupport.stream(clients.spliterator(), false).collect(Collectors.toSet());
    }
}
