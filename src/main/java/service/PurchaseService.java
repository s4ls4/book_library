package service;

import domain.Book;
import domain.Client;
import domain.Purchase;
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

public class PurchaseService {
    private PagingRepository<Long, Purchase> repository;


    private int page = 0;
    private int size = 1;

    public PurchaseService(PagingRepository<Long, Purchase> repository) {
        this.repository = repository;
    }

    //COPY THIS TO THE OTHER SERVICES
    public void setPageSize(int size) {
        this.size = size;
        this.page = 0;
    }

    //COPY THIS TO THE OTHER SERVICES
    public Set<Purchase> getNextPurchase() {
        Pageable pageable = PageRequest.of(size, page);
        try{
            Page<Purchase> purchasePage = repository.findAll(pageable);
            page = purchasePage.nextPageable().getPageNumber();
            return purchasePage.getContent().collect(Collectors.toSet());
        }catch (IndexOutOfBoundsException ex) {
            page = 0;
            return new HashSet<>();
        }
    }

    public void addPurchase(Purchase purchase) throws ValidatorException {
        repository.save(Optional.ofNullable(purchase));
    }

    public Set getAllPurchases() {
        Iterable<Purchase> purchases = this.repository.findAll();
        return (Set) StreamSupport.stream(purchases.spliterator(), false).collect(Collectors.toSet());
    }
}
