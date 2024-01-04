package com.DiegoEvangeliste.ControlFacturasProveedores.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "proveedores")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "razonSocial")
    private String razonSocial;

    @Column(name = "cuit")
    private String cuit;

    @Column(name = "domicilio")
    private String domicilio;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "email")
    private String email;

        //Relacion VUELTA con Factura
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "proveedor")
//    private List<Factura> facturas;

}
