package com.DiegoEvangeliste.ControlFacturasProveedores.service.impl;

import com.DiegoEvangeliste.ControlFacturasProveedores.dto.FacturaDTO;
import com.DiegoEvangeliste.ControlFacturasProveedores.model.entity.Factura;
import com.DiegoEvangeliste.ControlFacturasProveedores.repository.FacturaRepository;
import com.DiegoEvangeliste.ControlFacturasProveedores.service.IFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FacturaServiceImpl implements IFacturaService {

    @Autowired
    FacturaRepository repository;

    @Override
    public FacturaDTO save(Factura factura){
        if (!repository.existsById(factura.getId())) {
            return FacturaDTO.fromFactura(repository.save(factura));
        } else
            return null;
    }

    @Override
    public FacturaDTO update(Factura factura) {
        Optional<Factura> optional = repository.findById(factura.getId());
        if (optional.isPresent()) {
            optional.get().setEstadoPago(factura.getEstadoPago());
            optional.get().setPuntoVenta(factura.getPuntoVenta());
            optional.get().setNumeroComprobante(factura.getNumeroComprobante());
            optional.get().setNumeroRemito(factura.getNumeroRemito());
            optional.get().setFechaEmision(factura.getFechaEmision());
            repository.saveAndFlush(optional.get());
        }
        return FacturaDTO.fromFactura(optional.get());
    }

    @Override
    public boolean deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else
            return false;
    }

    @Override
    public FacturaDTO findById(Long id) {
        return FacturaDTO.fromFactura(repository.findById(id).orElseThrow());
    }

    @Override
    public List<FacturaDTO> findAll() {
        List<Factura> facturas = repository.findAll();
        List<FacturaDTO> dtos = new ArrayList<>();
        if (!facturas.isEmpty())
            for (Factura factura : facturas)
                dtos.add(FacturaDTO.fromFactura(factura));
        return dtos;
    }
}
