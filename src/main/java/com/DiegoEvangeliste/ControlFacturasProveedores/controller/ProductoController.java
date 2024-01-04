package com.DiegoEvangeliste.ControlFacturasProveedores.controller;

import com.DiegoEvangeliste.ControlFacturasProveedores.model.entity.Producto;
import com.DiegoEvangeliste.ControlFacturasProveedores.service.impl.ProductoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoServiceImpl service;

    @PostMapping
    public ResponseEntity<Producto> save(@RequestBody Producto producto){
        return service.save(producto);
    }

    @PutMapping
    public ResponseEntity<Producto> update(@RequestBody Producto producto){
        return service.update(producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Producto> deleteById(@PathVariable Long id){
        return service.deleteById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> findById(@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping
    public ResponseEntity<List<Producto>> findAll(){
        return service.findAll();
    }
}
