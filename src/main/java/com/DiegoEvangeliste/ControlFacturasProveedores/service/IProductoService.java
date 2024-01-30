package com.DiegoEvangeliste.ControlFacturasProveedores.service;

import com.DiegoEvangeliste.ControlFacturasProveedores.dto.ProductoDTO;
import com.DiegoEvangeliste.ControlFacturasProveedores.model.entity.Producto;

import java.util.List;

public interface IProductoService {
    ProductoDTO save(Producto producto);
    ProductoDTO update(Producto producto);
    ProductoDTO findById(Long id);
    List<ProductoDTO> findAll();
    boolean deleteById(Long id);
}
