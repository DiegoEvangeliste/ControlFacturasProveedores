package com.DiegoEvangeliste.ControlFacturasProveedores.service.impl;

import com.DiegoEvangeliste.ControlFacturasProveedores.dto.ProductoDTO;
import com.DiegoEvangeliste.ControlFacturasProveedores.model.entity.Producto;
import com.DiegoEvangeliste.ControlFacturasProveedores.repository.ProductoRepository;
import com.DiegoEvangeliste.ControlFacturasProveedores.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    ProductoRepository repository;

    @Override
    public ProductoDTO save(Producto producto){
        return ProductoDTO.fromProducto(repository.save(producto));
    }

    @Override
    public ProductoDTO update(Producto producto) {
        Optional<Producto> optional = repository.findById(producto.getId());
        if (optional.isPresent()) {
            optional.get().setDescripcion(producto.getDescripcion());
            optional.get().setPrecio(producto.getPrecio());
            repository.saveAndFlush(optional.get());
        }
        return ProductoDTO.fromProducto(optional.get());
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
    public ProductoDTO findById(Long id) {
        return ProductoDTO.fromProducto(repository.findById(id).orElseThrow());
    }

    @Override
    public List<ProductoDTO> findAll() {
        List<Producto> productos = repository.findAll();
        List<ProductoDTO> dtos = new ArrayList<>();
        if (!productos.isEmpty())
            for (Producto producto : productos)
                dtos.add(ProductoDTO.fromProducto(producto));
        return dtos;
    }

}
