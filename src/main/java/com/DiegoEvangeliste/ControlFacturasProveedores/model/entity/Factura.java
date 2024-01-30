package com.DiegoEvangeliste.ControlFacturasProveedores.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "estado_pago")
    private Boolean estadoPago;

    @Column(name = "punto_venta")
    private Integer puntoVenta;

    @Column(name = "numero_comprobante")
    private Integer numeroComprobante;

    @Column(name = "numero_remito")
    private Integer numeroRemito;

    @Column(name = "fecha_emision")
    @Temporal(TemporalType.DATE)
    private LocalDate fechaEmision;

}