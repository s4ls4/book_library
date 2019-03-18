package service;
import domain.Client;
import repository.Repository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class XMLClientService {
    private Repository<Long, Client> xmlRepo;


    public XMLClientService(Repository<Long, Client> xmlRepo) {
        this.xmlRepo = xmlRepo;
    }

    public void addClient(Client Client) {

        xmlRepo.save(Client);
    }

    public Set getAllClients() {
        Iterable<Client> Clients = this.xmlRepo.findAll();
        return (Set) StreamSupport.stream(Clients.spliterator(), false).collect(Collectors.toSet());
    }

    public void deleteClient(Long ID) {
        xmlRepo.delete(ID);
    }
}
