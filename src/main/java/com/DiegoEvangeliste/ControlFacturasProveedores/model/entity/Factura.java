package com.DiegoEvangeliste.ControlFacturasProveedores.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

        // Relacion IDA con Proveedor
//    @ManyToOne
//    @JoinColumn(name="id_proveedor")
//    private Proveedor proveedor;

        // Relacion IDA con Producto
//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(name="tbl_facturas_productos", joinColumns = @JoinColumn(name="id_factura"), inverseJoinColumns = @JoinColumn(name="id_producto"),  uniqueConstraints = @UniqueConstraint(columnNames = {"id_factura", "id_producto"}))
//    private List<Producto> productos;

}