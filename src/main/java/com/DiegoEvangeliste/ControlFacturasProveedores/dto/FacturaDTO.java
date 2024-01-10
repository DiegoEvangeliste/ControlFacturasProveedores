package com.DiegoEvangeliste.ControlFacturasProveedores.dto;

import java.time.LocalDate;

public record FacturaDTO(Boolean estadoPago,
                         Integer puntoVenta,
                         Integer numeroComprobante,
                         Integer numeroRemito,
                         LocalDate fechaEmision) {}
