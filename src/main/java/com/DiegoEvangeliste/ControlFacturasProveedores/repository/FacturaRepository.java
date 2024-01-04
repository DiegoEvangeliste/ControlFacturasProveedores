package com.DiegoEvangeliste.ControlFacturasProveedores.repository;

import com.DiegoEvangeliste.ControlFacturasProveedores.model.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<Factura,Long>{
}
