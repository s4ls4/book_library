package service;
import domain.Book;
import domain.Client;
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

public class XMLClientService {
    private PagingRepository<Long, Client> xmlRepo;

    private int page = 0;
    private int size = 1;


    public XMLClientService(PagingRepository<Long, Client> xmlRepo) {
        this.xmlRepo = xmlRepo;
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
            Page<Client> clientPage = xmlRepo.findAll(pageable);
            page = clientPage.nextPageable().getPageNumber();
            return clientPage.getContent().collect(Collectors.toSet());
        }catch (IndexOutOfBoundsException ex) {
            page = 0;
            return new HashSet<>();
        }
    }

    public void addClient(Client Client) {

        xmlRepo.save(Optional.ofNullable(Client));
    }

    public Set getAllClients() {
        Iterable<Client> Clients = this.xmlRepo.findAll();
        return (Set) StreamSupport.stream(Clients.spliterator(), false).collect(Collectors.toSet());
    }

    public void deleteClient(Long ID) {
        xmlRepo.delete(Optional.ofNullable(ID));
    }
}
