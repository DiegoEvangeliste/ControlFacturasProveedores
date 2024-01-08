package com.DiegoEvangeliste.ControlFacturasProveedores.controller;

import com.DiegoEvangeliste.ControlFacturasProveedores.model.entity.Proveedor;
import com.DiegoEvangeliste.ControlFacturasProveedores.service.impl.ProveedorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorServiceImpl service;

    @PostMapping
    public ResponseEntity<Proveedor> save(@RequestBody Proveedor proveedor){
        return service.save(proveedor);
    }

    @PatchMapping
    public ResponseEntity<Proveedor> update(@RequestBody Proveedor proveedor){
        return service.update(proveedor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Proveedor> deleteById(@PathVariable Long id){
        return service.deleteById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> findById(@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping
    public ResponseEntity<List<Proveedor>> findAll(){
        return service.findAll();
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<Proveedor>> findAllByPages(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        Pageable pageable  = PageRequest.of(page, size);
        return service.findAllByPages(pageable);
    }

}
