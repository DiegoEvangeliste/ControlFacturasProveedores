package com.DiegoEvangeliste.ControlFacturasProveedores.repository;

import com.DiegoEvangeliste.ControlFacturasProveedores.model.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor,Long> {
}
