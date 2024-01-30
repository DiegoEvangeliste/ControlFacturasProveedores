package com.DiegoEvangeliste.ControlFacturasProveedores.service;

import com.DiegoEvangeliste.ControlFacturasProveedores.dto.FacturaDTO;
import com.DiegoEvangeliste.ControlFacturasProveedores.model.entity.Factura;

import java.util.List;

public interface IFacturaService {
    FacturaDTO save(Factura factura);
    FacturaDTO update(Factura factura);
    FacturaDTO findById(Long id);
    List<FacturaDTO> findAll();
    boolean deleteById(Long id);
}
