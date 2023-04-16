package com.draen.data.client.repository;

import com.draen.domain.entity.Client;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    Optional<Client> findByPhoneNumber(String phoneNumber);

    @Cacheable("clients")
    Optional<Client> findByPhoneNumberEqualsAndBalanceGreaterThan(String phoneNumber, int money);
}
