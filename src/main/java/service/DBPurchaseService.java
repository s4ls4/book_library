package service;

import domain.Purchase;
import domain.validators.ValidatorException;
import repository.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class DBPurchaseService {
    private Repository<Long, Purchase> dbRepo;

    public DBPurchaseService(Repository<Long, Purchase> dbRepo) {
        this.dbRepo = dbRepo;
    }

    public void addPurchase(Purchase purchase) throws ValidatorException {
        dbRepo.save(Optional.ofNullable(purchase));
    }

    public Set getAllPurchases() {
        Iterable<Purchase> purchases = this.dbRepo.findAll();
        return (Set) StreamSupport.stream(purchases.spliterator(), false).collect(Collectors.toSet());
    }
}
