package com.DiegoEvangeliste.ControlFacturasProveedores.dto;

import com.DiegoEvangeliste.ControlFacturasProveedores.model.entity.Factura;

import java.time.LocalDate;

public record FacturaDTO(Boolean estadoPago, Integer puntoVenta, Integer numeroComprobante, Integer numeroRemito, LocalDate fechaEmision) {

    public static FacturaDTO fromFactura(Factura factura) {
        return new FacturaDTO(factura.getEstadoPago(), factura.getPuntoVenta(),
                factura.getNumeroComprobante(), factura.getNumeroRemito(), factura.getFechaEmision());
    }

}
