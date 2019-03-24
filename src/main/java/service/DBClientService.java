package service;

import domain.Client;
import domain.validators.ValidatorException;
import repository.Repository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class DBClientService {
    private Repository<Long, Client> dbRepo;

    public DBClientService(Repository<Long,Client> dbRepo){
        this.dbRepo = dbRepo;
    }

    public void addClient(Client client) throws ValidatorException {
        dbRepo.save(client);
    }

    public void deleteClient(Long id) throws ValidatorException{
        dbRepo.delete(id);
    }

    public Set getAllClients() {
        Iterable<Client> client = this.dbRepo.findAll();
        return (Set) StreamSupport.stream(client.spliterator(), false).collect(Collectors.toSet());
    }

    public void updateClient(Client client) throws ValidatorException{
        dbRepo.update(client);
    }

}
