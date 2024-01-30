package com.DiegoEvangeliste.ControlFacturasProveedores.service.impl;

import com.DiegoEvangeliste.ControlFacturasProveedores.dto.ProveedorDTO;
import com.DiegoEvangeliste.ControlFacturasProveedores.model.entity.Proveedor;
import com.DiegoEvangeliste.ControlFacturasProveedores.repository.ProveedorRepository;
import com.DiegoEvangeliste.ControlFacturasProveedores.service.IProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServiceImpl implements IProveedorService {

    @Autowired
    ProveedorRepository repository;

    @Override
    public ProveedorDTO save(Proveedor proveedor){
        if (!repository.existsById(proveedor.getId()))
            return ProveedorDTO.fromProveedor(repository.save(proveedor));
        return null;
    }

    @Override
    public ProveedorDTO update(Proveedor proveedor) {
        Optional<Proveedor> optional = repository.findById(proveedor.getId());
        if (optional.isPresent()) {
            optional.get().setRazonSocial(proveedor.getRazonSocial());
            optional.get().setCuit(proveedor.getCuit());
            optional.get().setDomicilio(proveedor.getDomicilio());
            optional.get().setTelefono(proveedor.getTelefono());
            optional.get().setEmail(proveedor.getEmail());
            repository.saveAndFlush(optional.get());
        }
        return ProveedorDTO.fromProveedor(optional.get());
    }

    @Override
    public boolean deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public ProveedorDTO findById(Long id) {
        return ProveedorDTO.fromProveedor(repository.findById(id).orElseThrow());
    }

    @Override
    public List<ProveedorDTO> findAll() {
        List<Proveedor> proveedores = repository.findAll();
        if (!proveedores.isEmpty()){
            List<ProveedorDTO> dtos = new ArrayList<>();
            for (Proveedor proveedor : proveedores)
                dtos.add(ProveedorDTO.fromProveedor(proveedor));
            return dtos;
        }
        return null;
    }

    @Override
    public List<ProveedorDTO> findByRazonSocial(String razonSocial) {
        List<Proveedor> proveedores = repository.findByRazonSocial(razonSocial);
        List<ProveedorDTO> dtos = new ArrayList<>();
        if (!proveedores.isEmpty())
            for (Proveedor proveedor : proveedores)
                dtos.add(ProveedorDTO.fromProveedor(proveedor));
        return dtos;
    }

    @Override
    public List<String> findAllEmails() {
        return repository.findAllEmails();
    }

    @Override
    public Page<Proveedor> findAllByPages(Pageable pageable) {
        return  repository.findAll(pageable);
    }

}
