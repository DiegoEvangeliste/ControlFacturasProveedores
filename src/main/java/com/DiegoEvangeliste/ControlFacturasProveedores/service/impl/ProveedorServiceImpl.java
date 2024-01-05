package com.DiegoEvangeliste.ControlFacturasProveedores.service.impl;

import com.DiegoEvangeliste.ControlFacturasProveedores.model.entity.Proveedor;
import com.DiegoEvangeliste.ControlFacturasProveedores.repository.ProveedorRepository;
import com.DiegoEvangeliste.ControlFacturasProveedores.service.IProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServiceImpl implements IProveedorService {

    @Autowired
    ProveedorRepository repository;

    @Override
    public ResponseEntity<Proveedor> save(Proveedor proveedor){
        if (!repository.existsById(proveedor.getId())) {
            repository.save(proveedor);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Proveedor> update(Proveedor proveedor) {
        Optional<Proveedor> optional = repository.findById(proveedor.getId());
        if (optional.isPresent()) {
            optional.get().setRazonSocial(proveedor.getRazonSocial());
            optional.get().setCuit(proveedor.getCuit());
            optional.get().setDomicilio(proveedor.getDomicilio());
            optional.get().setTelefono(proveedor.getTelefono());
            optional.get().setEmail(proveedor.getEmail());
            repository.saveAndFlush(optional.get());
            return ResponseEntity.ok(optional.get());
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Proveedor> deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Proveedor> findById(Long id) {
        Optional<Proveedor> optional = repository.findById(id);
        return ResponseEntity.ok(optional.orElseThrow());
    }

    @Override
    public ResponseEntity<List<Proveedor>> findAll() {
        List<Proveedor> list = repository.findAll();
        if (!list.isEmpty())
            return ResponseEntity.ok(list);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
