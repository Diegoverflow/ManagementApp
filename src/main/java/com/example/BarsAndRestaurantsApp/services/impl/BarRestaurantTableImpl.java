package com.example.BarsAndRestaurantsApp.services.impl;

import com.example.BarsAndRestaurantsApp.domain.entities.BarRestaurantTableEntity;
import com.example.BarsAndRestaurantsApp.repositories.BarRestaurantTableRepository;
import com.example.BarsAndRestaurantsApp.services.BarRestaurantTableService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BarRestaurantTableImpl implements BarRestaurantTableService {

    private BarRestaurantTableRepository repository;

    public BarRestaurantTableImpl(BarRestaurantTableRepository repository) {
        this.repository = repository;
    }

    @Override
    public BarRestaurantTableEntity save(BarRestaurantTableEntity table) {
        return repository.save(table);
    }

    @Override
    public Page<BarRestaurantTableEntity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<BarRestaurantTableEntity> findOne(Integer id) {
        return repository.findById(id);
    }

    @Override
    public boolean exits(Integer id) {
        return repository.existsById(id);
    }

    /*
    @Override
    public BarRestaurantTableEntity partialUpdate(Integer id, BarRestaurantTableEntity table) {
        table.setId(id);
        return repository.findById(id).map(
                existingTable ->{
                    Optional.ofNullable(table.getTickets()).ifPresent(existingTable::setTickets);
                    return repository.save(existingTable);
                }
        ).orElseThrow(() -> new RuntimeException("Order does not exist"));
    }*/

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
