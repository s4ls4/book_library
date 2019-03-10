package repository;

/**
 * Author: Stefi Nicoara
 */

import domain.BaseEntity;
import domain.validators.ValidatorException;

import java.util.Optional;

public interface Repository<ID, T extends BaseEntity<ID>> {
    Optional<T> findOne(ID var1);

    Iterable<T> findAll();

    Optional<T> save(T var1) throws ValidatorException;
}