package com.DiegoEvangeliste.ControlFacturasProveedores.controller;

import com.DiegoEvangeliste.ControlFacturasProveedores.dto.ProveedorDTO;
import com.DiegoEvangeliste.ControlFacturasProveedores.model.entity.Proveedor;
import com.DiegoEvangeliste.ControlFacturasProveedores.service.impl.ProveedorServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorServiceImpl service;

    @PostMapping
    @Operation(summary = "Save Proveedor", description = "Save an Proveedor in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The Proveedor was saved successfully"),
            @ApiResponse(responseCode = "400", description = "Error saving Proveedor to database")})
    public ResponseEntity<ProveedorDTO> save(@RequestBody Proveedor proveedor){
        Optional<ProveedorDTO> optional = Optional.ofNullable(service.save(proveedor));
        if (optional.isPresent())
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping
    @Operation(summary = "Update Proveedor", description = "Update an Proveedor from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The Proveedor was updated successfully"),
            @ApiResponse(responseCode = "400", description = "Error update Proveedor to database")})
    public ResponseEntity<ProveedorDTO> update(@RequestBody Proveedor proveedor){
        Optional<ProveedorDTO> optional =  Optional.ofNullable(service.update(proveedor));
        if (optional.isPresent())
            return ResponseEntity.ok(optional.get());
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Proveedor by Id", description = "An Proveedor is deleted by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The Proveedor was successfully deleted from the database"),
            @ApiResponse(responseCode = "404", description = "The Proveedor was not found in the database")})
    public ResponseEntity<ProveedorDTO> deleteById(@PathVariable Long id){
        if (service.deleteById(id))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find Proveedor by Id", description = "Search for an Proveedor by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The Proveedor was found by the id in the database correctly"),
            @ApiResponse(responseCode = "404", description = "The Proveedor was not found in the database")})
    public ResponseEntity<ProveedorDTO> findById(@PathVariable Long id){
        Optional<ProveedorDTO> optional = Optional.ofNullable(service.findById(id));
        if (optional.isPresent())
            return ResponseEntity.ok(optional.get());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    @Operation(summary = "Find all Proveedores", description = "Get all Proveedor from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedores were found in the database"),
            @ApiResponse(responseCode = "404", description = "There is no Proveedor in the database")})
    public ResponseEntity<List<ProveedorDTO>> findAll() {
        Optional<List<ProveedorDTO>> optional = Optional.ofNullable(service.findAll());
        if (!optional.get().isEmpty())
            return ResponseEntity.ok(optional.get());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/razonSocial/{razonSocial}")
    @Operation(summary = "Find Proveedores by Razon Social", description = "Search for Proveedores by Razon Social")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedores were found by the Razon Social in the database correctly"),
            @ApiResponse(responseCode = "404", description = "The Proveedores was not found in the database")})
    public ResponseEntity<List<ProveedorDTO>> findByRazonSocial(@PathVariable String razonSocial) {
        List<ProveedorDTO> proveedores = service.findByRazonSocial(razonSocial);
        if (!proveedores.isEmpty())
            return ResponseEntity.ok(proveedores);
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/emails")
    @Operation(summary = "Find Proveedores by Email", description = "Search for an Proveedores by Emails")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedores were found by the Email in the database correctly"),
            @ApiResponse(responseCode = "404", description = "The Proveedores was not found in the database")})
    public ResponseEntity<List<String>> findAllEmails() {
        List<String> emails = service.findAllEmails();
        if (!emails.isEmpty())
            return ResponseEntity.ok(emails);
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/paged")
    @Operation(summary = "Find all Proveedores by pages", description = "Search for all Proveedores on pages")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedores was found by pages in the database correctly"),
            @ApiResponse(responseCode = "404", description = "The Proveedores was not found in the database")})
    public ResponseEntity<Page<Proveedor>> findAllByPages(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        Pageable pageable  = PageRequest.of(page, size);
        Optional<Page<Proveedor>> optional = Optional.ofNullable(service.findAllByPages(pageable));
        if (optional.isPresent())
            return ResponseEntity.ok(optional.get());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
