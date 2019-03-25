package service;

import domain.Book;
import domain.Client;
import domain.validators.ValidatorException;
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

/**
 * author: Stefi Nicoara
 */
public class ClientService {
    private PagingRepository<Long, Client> repository;

    private int page = 0;
    private int size = 1;


    /**
     * constructor for the client service
     * @param repository - the repository
     */
    public ClientService(PagingRepository<Long, Client> repository) {
        this.repository = repository;
    }

    //COPY THIS TO THE OTHER SERVICES
    public void setPageSize(int size) {
        this.size = size;
        this.page = 0;
    }

    //COPY THIS TO THE OTHER SERVICES
    public Set<Client> getNextClient() {
        Pageable pageable = PageRequest.of(size, page);
        try{
            Page<Client> clientPage = repository.findAll(pageable);
            page = clientPage.nextPageable().getPageNumber();
            return clientPage.getContent().collect(Collectors.toSet());
        }catch (IndexOutOfBoundsException ex) {
            page = 0;
            return new HashSet<>();
        }
    }

    /**
     * Function for adding a client
     * @param client the client object
     * @throws ValidatorException
     */
    public void addClient(Client client) throws ValidatorException {
        repository.save(Optional.ofNullable(client));
    }

    /**
     * Function for accessing all the entities
     * @return a stream with all the objects
     */
    public Set getAllClients() {
        Iterable<Client> clients = this.repository.findAll();
        return (Set) StreamSupport.stream(clients.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     * Function that deletes a client
     * @param id the id of the required client
     */
    public void deleteClient(Long id) {
        repository.delete(Optional.ofNullable(id));
    }

    /**
     * Dunction that updates a client
     * @param client the new object
     * @throws ValidatorException
     */
    public void updateClient(Client client) throws ValidatorException{
        repository.update(Optional.ofNullable(client));
    }

}
