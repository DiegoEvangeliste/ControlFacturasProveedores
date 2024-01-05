package com.DiegoEvangeliste.ControlFacturasProveedores.controller;

import com.DiegoEvangeliste.ControlFacturasProveedores.model.entity.Factura;
import com.DiegoEvangeliste.ControlFacturasProveedores.service.impl.FacturaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/facturas")
public class FacturaController {

    @Autowired
    private FacturaServiceImpl service;

    @PostMapping
    public ResponseEntity<Factura> save(@RequestBody Factura factura){
        return service.save(factura);
    }

    @PatchMapping
    public ResponseEntity<Factura> update(@RequestBody Factura factura){
        return service.update(factura);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Factura> deleteById(@PathVariable Long id){
        return service.deleteById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> findById(@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping
    public ResponseEntity<List<Factura>> findAll(){
        return service.findAll();
    }
}
