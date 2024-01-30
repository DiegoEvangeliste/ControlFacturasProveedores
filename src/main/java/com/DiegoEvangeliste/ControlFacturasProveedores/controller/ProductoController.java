package com.DiegoEvangeliste.ControlFacturasProveedores.controller;

import com.DiegoEvangeliste.ControlFacturasProveedores.dto.ProductoDTO;
import com.DiegoEvangeliste.ControlFacturasProveedores.model.entity.Producto;
import com.DiegoEvangeliste.ControlFacturasProveedores.service.impl.ProductoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Save Producto", description = "Save an Producto in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The Producto was saved successfully"),
            @ApiResponse(responseCode = "400", description = "Error saving Producto to database")})
    public ResponseEntity<ProductoDTO> save(@RequestBody Producto producto){
        Optional<ProductoDTO> optional = Optional.ofNullable(service.save(producto));
        if (optional.isPresent())
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping
    @Operation(summary = "Update Producto", description = "Update an Producto from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The Producto was updated successfully"),
            @ApiResponse(responseCode = "400", description = "Error update Producto to database")})
    public ResponseEntity<ProductoDTO> update(@RequestBody Producto producto){
        Optional<ProductoDTO> optional =  Optional.ofNullable(service.update(producto));
        if (optional.isPresent())
            return ResponseEntity.ok(optional.get());
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Producto by Id", description = "An Producto is deleted by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The Producto was successfully deleted from the database"),
            @ApiResponse(responseCode = "404", description = "The Producto was not found in the database")})
    public ResponseEntity<ProductoDTO> deleteById(@PathVariable Long id){
        if (service.deleteById(id))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find Producto by Id", description = "Search for an Producto by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The Producto was found by the id in the database correctly"),
            @ApiResponse(responseCode = "404", description = "The Producto was not found in the database")})
    public ResponseEntity<ProductoDTO> findById(@PathVariable Long id){
        Optional<ProductoDTO> optional = Optional.ofNullable(service.findById(id));
        if (optional.isPresent())
            return ResponseEntity.ok(optional.get());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    @Operation(summary = "Find all Productos", description = "Get all Producto from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos were found in the database"),
            @ApiResponse(responseCode = "404", description = "There is no Producto in the database")})
    public ResponseEntity<List<ProductoDTO>> findAll() {
        Optional<List<ProductoDTO>> optional = Optional.ofNullable(service.findAll());
        if (!optional.get().isEmpty())
            return ResponseEntity.ok(optional.get());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
