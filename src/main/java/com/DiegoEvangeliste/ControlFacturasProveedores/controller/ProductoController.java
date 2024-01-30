package com.DiegoEvangeliste.ControlFacturasProveedores.controller;

import com.DiegoEvangeliste.ControlFacturasProveedores.dto.ProductoDTO;
import com.DiegoEvangeliste.ControlFacturasProveedores.model.entity.Producto;
import com.DiegoEvangeliste.ControlFacturasProveedores.service.impl.ProductoServiceImpl;
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
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoServiceImpl service;

    @PostMapping
    public ResponseEntity<ProductoDTO> save(@RequestBody Producto producto){
        Optional<ProductoDTO> optional = Optional.ofNullable(service.save(producto));
        if (optional.isPresent())
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping
    public ResponseEntity<ProductoDTO> update(@RequestBody Producto producto){
        Optional<ProductoDTO> optional =  Optional.ofNullable(service.update(producto));
        if (optional.isPresent())
            return ResponseEntity.ok(optional.get());
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductoDTO> deleteById(@PathVariable Long id){
        if (service.deleteById(id))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> findById(@PathVariable Long id){
        Optional<ProductoDTO> optional = Optional.ofNullable(service.findById(id));
        if (optional.isPresent())
            return ResponseEntity.ok(optional.get());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> findAll() {
        Optional<List<ProductoDTO>> optional = Optional.ofNullable(service.findAll());
        if (!optional.get().isEmpty())
            return ResponseEntity.ok(optional.get());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
