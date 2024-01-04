package com.DiegoEvangeliste.ControlFacturasProveedores.service.impl;

import com.DiegoEvangeliste.ControlFacturasProveedores.model.entity.Factura;
import com.DiegoEvangeliste.ControlFacturasProveedores.repository.FacturaRepository;
import com.DiegoEvangeliste.ControlFacturasProveedores.service.IFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaServiceImpl implements IFacturaService {

    @Autowired
    FacturaRepository repository;

    @Override
    public ResponseEntity<Factura> save(Factura factura){
        if (!repository.existsById(factura.getId())) {
            repository.save(factura);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Factura> update(Factura factura) {
        Optional<Factura> optional = repository.findById(factura.getId());
        if (optional.isPresent()) {
            optional.get().setEstadoPago(factura.getEstadoPago());
            optional.get().setPuntoVenta(factura.getPuntoVenta());
            optional.get().setNumeroComprobante(factura.getNumeroComprobante());
            optional.get().setNumeroRemito(factura.getNumeroRemito());
            optional.get().setFechaEmision(factura.getFechaEmision());
            return ResponseEntity.ok(optional.get());
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Factura> deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Factura> findById(Long id) {
        Optional<Factura> optional = repository.findById(id);
        return ResponseEntity.ok(optional.orElseThrow());
    }

    @Override
    public ResponseEntity<List<Factura>> findAll() {
        List<Factura> list = repository.findAll();
        if (!list.isEmpty())
            return ResponseEntity.ok(list);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
