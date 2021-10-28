package com.formacionti.springboot.app.item.service;

import com.formacionti.springboot.app.item.clients.ProductoClientRest;
import com.formacionti.springboot.app.item.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("serviceFeign")
@Primary
public class ItemServiceFeign implements ItemService {

    @Autowired
    private ProductoClientRest clientRest;

    @Override
    public List<Item> findAll() {
        return clientRest.listar()
                .stream()
                .map(p -> new Item(p, 1))
                .collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        return new Item(clientRest.detalle(id), cantidad);
    }
}
