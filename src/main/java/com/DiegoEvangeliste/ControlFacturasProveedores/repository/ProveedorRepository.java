package com.DiegoEvangeliste.ControlFacturasProveedores.repository;

import com.DiegoEvangeliste.ControlFacturasProveedores.model.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor,Long> {
        // Custom Queries
    @Query(value = "SELECT p.email FROM PROVEEDORES p", nativeQuery = true)
    List<String> findAllEmails();

        // Query Methods
    List<Proveedor> findByRazonSocial(String razonSocial);

}
