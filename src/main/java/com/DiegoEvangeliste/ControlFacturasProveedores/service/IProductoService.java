package com.DiegoEvangeliste.ControlFacturasProveedores.service;

import com.DiegoEvangeliste.ControlFacturasProveedores.model.entity.Producto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProductoService {
    ResponseEntity<Producto> save(Producto producto);
    ResponseEntity<Producto> update(Producto producto);
    ResponseEntity<Producto> findById(Long id);
    ResponseEntity<List<Producto>> findAll();
    ResponseEntity<Producto> deleteById(Long id);
}
