package com.DiegoEvangeliste.ControlFacturasProveedores.controller;

import com.DiegoEvangeliste.ControlFacturasProveedores.dto.FacturaDTO;
import com.DiegoEvangeliste.ControlFacturasProveedores.model.entity.Factura;
import com.DiegoEvangeliste.ControlFacturasProveedores.service.impl.Datos;
import com.DiegoEvangeliste.ControlFacturasProveedores.service.impl.FacturaServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Slf4j
class FacturaControllerTest {

    @Mock
    private FacturaServiceImpl facturaService;

    @InjectMocks
    private FacturaController facturaController;

    private Factura factura;
    private Factura facturaAux = Datos.FACTURA_1;

    @BeforeEach
    void setUp() {
        factura = Factura.builder().id(facturaAux.getId()).estadoPago(facturaAux.getEstadoPago())
                .puntoVenta(facturaAux.getPuntoVenta()).numeroComprobante(facturaAux.getNumeroComprobante())
                .numeroRemito(facturaAux.getNumeroRemito()).fechaEmision(facturaAux.getFechaEmision())
                .build();
    }

    @Test
    void saveOKTest() {
        Optional<FacturaDTO> optional = Optional.of(FacturaDTO.fromFactura(factura));
        when(facturaService.save(any(Factura.class))).thenReturn(FacturaDTO.fromFactura(factura));

        ResponseEntity<FacturaDTO> response = facturaController.save(factura);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(FacturaDTO.fromFactura(factura), response.getBody());
        verify(facturaService).save(factura);
        verify(facturaService, times(1)).save(factura);
    }


    @Test
    void updateOkTest() {
        FacturaDTO facturaAux = FacturaDTO.fromFactura(Datos.FACTURA_2);

        when(facturaService.update(factura)).thenReturn(facturaAux);

        factura.setEstadoPago(facturaAux.estadoPago());
        factura.setPuntoVenta(facturaAux.puntoVenta());
        factura.setNumeroComprobante(facturaAux.numeroComprobante());
        factura.setNumeroRemito(facturaAux.numeroRemito());
        factura.setFechaEmision(facturaAux.fechaEmision());
        ResponseEntity<FacturaDTO> response = facturaController.update(factura);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(facturaAux, response.getBody());
        verify(facturaService).update(factura);
        verify(facturaService, times(1)).update(factura);
    }

    @Test
    void deleteByIdOkTest() {
        when(facturaService.deleteById(any(Long.class))).thenReturn(true);

        ResponseEntity<FacturaDTO> response = facturaController.deleteById(any(Long.class));

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(facturaService).deleteById(any(Long.class));
        verify(facturaService, times(1)).deleteById(any(Long.class));
    }

    @Test
    void findByIdOkTest() {
        Optional<FacturaDTO> optional = Optional.of(FacturaDTO.fromFactura(Datos.FACTURA_1));

        when(facturaService.findById(factura.getId())).thenReturn(FacturaDTO.fromFactura(factura));

        ResponseEntity<FacturaDTO> response = facturaController.findById(factura.getId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(optional.get(), response.getBody());
        verify(facturaService).findById(factura.getId());
        verify(facturaService, times(1)).findById(factura.getId());
    }

    @Test
    void findAllOkTest() {
        Optional<List<FacturaDTO>> optionalFacturaDTOList = Optional.of(Datos.FACTURA_DTO_LIST);

        when(facturaService.findAll()).thenReturn(Datos.FACTURA_DTO_LIST);

        ResponseEntity<List<FacturaDTO>> response = facturaController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(optionalFacturaDTOList.get().size(), response.getBody().size());
        verify(facturaService).findAll();
        verify(facturaService, times(1)).findAll();
    }
}