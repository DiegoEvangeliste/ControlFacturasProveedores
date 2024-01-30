package com.DiegoEvangeliste.ControlFacturasProveedores.controller;

import com.DiegoEvangeliste.ControlFacturasProveedores.dto.FacturaDTO;
import com.DiegoEvangeliste.ControlFacturasProveedores.model.entity.Factura;
import com.DiegoEvangeliste.ControlFacturasProveedores.service.impl.FacturaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/facturas")
public class FacturaController {

    @Autowired
    private FacturaServiceImpl service;

    @PostMapping
    public ResponseEntity<FacturaDTO> save(@RequestBody Factura factura){
        Optional<FacturaDTO> optional = Optional.ofNullable(service.save(factura));
        if (optional.isPresent())
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping
    public ResponseEntity<FacturaDTO> update(@RequestBody Factura factura){
        Optional<FacturaDTO> optional =  Optional.ofNullable(service.update(factura));
        if (optional.isPresent())
            return ResponseEntity.ok(optional.get());
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FacturaDTO> deleteById(@PathVariable Long id){
        if (service.deleteById(id))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaDTO> findById(@PathVariable Long id){
        Optional<FacturaDTO> optional = Optional.ofNullable(service.findById(id));
        if (optional.isPresent())
            return ResponseEntity.ok(optional.get());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<FacturaDTO>> findAll() {
        Optional<List<FacturaDTO>> optional = Optional.ofNullable(service.findAll());
        if (!optional.get().isEmpty())
            return ResponseEntity.ok(optional.get());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
