package com.DiegoEvangeliste.ControlFacturasProveedores.service.impl;

import com.DiegoEvangeliste.ControlFacturasProveedores.model.entity.Producto;
import com.DiegoEvangeliste.ControlFacturasProveedores.repository.ProductoRepository;
import com.DiegoEvangeliste.ControlFacturasProveedores.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    ProductoRepository repository;

    @Override
    public ResponseEntity<Producto> save(Producto producto){
        if (!repository.existsById(producto.getId())) {
            repository.save(producto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Producto> update(Producto producto) {
        Optional<Producto> optional = repository.findById(producto.getId());
        if (optional.isPresent()) {
            optional.get().setDescripcion(producto.getDescripcion());
            optional.get().setPrecio(producto.getPrecio());
            repository.saveAndFlush(optional.get());
            return ResponseEntity.ok(optional.get());
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Producto> deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Producto> findById(Long id) {
        Optional<Producto> optional = repository.findById(id);
        return ResponseEntity.ok(optional.orElseThrow());
    }

    @Override
    public ResponseEntity<List<Producto>> findAll() {
        List<Producto> list = repository.findAll();
        if (!list.isEmpty())
            return ResponseEntity.ok(list);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
