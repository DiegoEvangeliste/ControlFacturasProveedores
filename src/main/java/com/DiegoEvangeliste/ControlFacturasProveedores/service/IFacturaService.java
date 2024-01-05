package com.DiegoEvangeliste.ControlFacturasProveedores.service;

import com.DiegoEvangeliste.ControlFacturasProveedores.model.entity.Factura;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IFacturaService {
    ResponseEntity<Factura> save(Factura factura);
    ResponseEntity<Factura> update(Factura factura);
    ResponseEntity<Factura> findById(Long id);
    ResponseEntity<List<Factura>> findAll();
    ResponseEntity<Factura> deleteById(Long id);
}
