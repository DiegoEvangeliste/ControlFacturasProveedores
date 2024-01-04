package com.DiegoEvangeliste.ControlFacturasProveedores.service;

import com.DiegoEvangeliste.ControlFacturasProveedores.model.entity.Proveedor;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProveedorService {
    ResponseEntity<Proveedor> save(Proveedor proveedor);
    ResponseEntity<Proveedor> update(Proveedor proveedor);
    ResponseEntity<Proveedor> findById(Long id);
    ResponseEntity<List<Proveedor>> findAll();
    ResponseEntity<Proveedor> deleteById(Long id);
}
