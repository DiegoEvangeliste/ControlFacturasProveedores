package com.DiegoEvangeliste.ControlFacturasProveedores.dto;

import com.DiegoEvangeliste.ControlFacturasProveedores.model.entity.Producto;

public record ProductoDTO(String descripcion, Double precio) {

    public static ProductoDTO fromProducto(Producto producto) {
        return new ProductoDTO(producto.getDescripcion(), producto.getPrecio());
    }
}
