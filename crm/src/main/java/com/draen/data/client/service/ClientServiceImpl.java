package com.draen.data.client.service;

import com.draen.data.payment.dto.PaymentDto;
import com.draen.domain.entity.Client;
import com.draen.domain.repository.ClientRepository;
import org.springframework.transaction.support.TransactionTemplate;

public class ClientServiceImpl implements ClientService {
    private final ClientRepository repository;
    private final TransactionTemplate template;

    public ClientServiceImpl(ClientRepository repository, TransactionTemplate template) {
        this.repository = repository;
        this.template = template;
    }

    @Override
    public Client create(Client client) {
        return template.execute(status -> {
            return repository.save(client);
        });
    }

    @Override
    public Client update(PaymentDto paymentDto) {
        return template.execute(status -> {
            Client client = repository.findByPhoneNumber(paymentDto.getNumberPhone())
                    .orElseThrow();
            int newMoney = client.getMoney() + paymentDto.getMoney();
            client.setMoney(newMoney);
            return repository.save(client);
        });

    }
}
