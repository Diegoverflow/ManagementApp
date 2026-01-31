package com.example.BarsAndRestaurantsApp.services.impl;

import com.example.BarsAndRestaurantsApp.domain.entities.CustomerOrderEntity;
import com.example.BarsAndRestaurantsApp.repositories.CustomerOrderRepository;
import com.example.BarsAndRestaurantsApp.services.CustomerOrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    private CustomerOrderRepository repository;

    public CustomerOrderServiceImpl(CustomerOrderRepository repository) {
        this.repository = repository;
    }


    @Override
    public CustomerOrderEntity save(CustomerOrderEntity order) {
        return repository.save(order);
    }

    @Override
    public Page<CustomerOrderEntity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<CustomerOrderEntity> findOne(UUID id) {
        return repository.findById(id);
    }

    @Override
    public boolean exits(UUID id) {
        return repository.existsById(id);
    }

    @Override
    public CustomerOrderEntity partialUpdate(UUID id, CustomerOrderEntity order) {
        order.setId(id);
        return repository.findById(id).map(
                existingOrder ->{
                    Optional.ofNullable(order.getProductQuantity()).ifPresent(existingOrder::setProductQuantity);
                    //Optional.ofNullable(order.getTicket()).ifPresent(existingOrder::setTicket);
                    Optional.ofNullable(order.getProduct()).ifPresent(existingOrder::setProduct);
                    return repository.save(existingOrder);
                }
        ).orElseThrow(() -> new RuntimeException("Order does not exist"));
    }

    @Override
    public void delete(UUID id) {

    }
}
