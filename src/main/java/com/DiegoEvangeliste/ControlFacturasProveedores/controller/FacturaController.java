package com.DiegoEvangeliste.ControlFacturasProveedores.controller;

import com.DiegoEvangeliste.ControlFacturasProveedores.dto.FacturaDTO;
import com.DiegoEvangeliste.ControlFacturasProveedores.model.entity.Factura;
import com.DiegoEvangeliste.ControlFacturasProveedores.service.impl.FacturaServiceImpl;
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
@RequestMapping("/facturas")
public class FacturaController {

    @Autowired
    private FacturaServiceImpl service;

    @PostMapping
    @Operation(summary = "Save Factura", description = "Save an Factura in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The Factura was saved successfully"),
            @ApiResponse(responseCode = "400", description = "Error saving Factura to database")})
    public ResponseEntity<FacturaDTO> save(@RequestBody Factura factura){
        Optional<FacturaDTO> optional = Optional.ofNullable(service.save(factura));
        if (optional.isPresent())
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping
    @Operation(summary = "Update Factura", description = "Update an Factura from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The Factura was updated successfully"),
            @ApiResponse(responseCode = "400", description = "Error update Factura to database")})
    public ResponseEntity<FacturaDTO> update(@RequestBody Factura factura){
        Optional<FacturaDTO> optional =  Optional.ofNullable(service.update(factura));
        if (optional.isPresent())
            return ResponseEntity.ok(optional.get());
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Factura by Id", description = "An Factura is deleted by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The Factura was successfully deleted from the database"),
            @ApiResponse(responseCode = "404", description = "The Factura was not found in the database")})
    public ResponseEntity<FacturaDTO> deleteById(@PathVariable Long id){
        if (service.deleteById(id))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find Factura by Id", description = "Search for an Factura by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The Factura was found by the id in the database correctly"),
            @ApiResponse(responseCode = "404", description = "The Factura was not found in the database")})
    public ResponseEntity<FacturaDTO> findById(@PathVariable Long id){
        Optional<FacturaDTO> optional = Optional.ofNullable(service.findById(id));
        if (optional.isPresent())
            return ResponseEntity.ok(optional.get());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    @Operation(summary = "Find all Facturas", description = "Get all Factura from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Facturas were found in the database"),
            @ApiResponse(responseCode = "404", description = "There is no Factura in the database")})
    public ResponseEntity<List<FacturaDTO>> findAll() {
        Optional<List<FacturaDTO>> optional = Optional.ofNullable(service.findAll());
        if (!optional.get().isEmpty())
            return ResponseEntity.ok(optional.get());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
