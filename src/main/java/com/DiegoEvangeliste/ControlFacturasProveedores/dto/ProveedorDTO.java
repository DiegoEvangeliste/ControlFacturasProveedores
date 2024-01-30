package com.DiegoEvangeliste.ControlFacturasProveedores.dto;

import com.DiegoEvangeliste.ControlFacturasProveedores.model.entity.Proveedor;

public record ProveedorDTO(String razonSocial, String cuit, String domicilio, String telefono, String email) {

    public static ProveedorDTO fromProveedor(Proveedor proveedor) {
        return new ProveedorDTO(proveedor.getRazonSocial(), proveedor.getCuit(), proveedor.getDomicilio(), proveedor.getTelefono(), proveedor.getEmail());
    }
}
