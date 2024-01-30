package com.DiegoEvangeliste.ControlFacturasProveedores.service;

import com.DiegoEvangeliste.ControlFacturasProveedores.dto.ProveedorDTO;
import com.DiegoEvangeliste.ControlFacturasProveedores.model.entity.Proveedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProveedorService {
    ProveedorDTO save(Proveedor proveedor);
    ProveedorDTO update(Proveedor proveedor);
    ProveedorDTO findById(Long id);
    List<ProveedorDTO> findAll();
    boolean deleteById(Long id);
    Page<Proveedor> findAllByPages(Pageable pageable);
    List<String> findAllEmails();
    List<ProveedorDTO> findByRazonSocial(String razonSocial);
}
