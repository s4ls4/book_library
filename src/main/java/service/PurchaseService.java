package service;

import domain.Book;
import domain.Client;
import domain.Purchase;
import domain.validators.ValidatorException;
import repository.Repository;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PurchaseService {
    private Repository<Long, Purchase> repository;

    public PurchaseService(Repository<Long, Purchase> repository) {
        this.repository = repository;
    }

    public void addPurchase(Purchase purchase) throws ValidatorException {
        repository.save(purchase);
    }

    public Set getAllPurchases() {
        Iterable<Purchase> purchases = this.repository.findAll();
        return (Set) StreamSupport.stream(purchases.spliterator(), false).collect(Collectors.toSet());
    }
}
