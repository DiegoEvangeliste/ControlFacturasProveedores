-- INSERT INTO proveedores
INSERT INTO proveedores (razon_social, cuit, domicilio, telefono, email) VALUES ('Proveedor A', '20345678901', 'Calle Principal 123', '1122334455', 'proveedora@example.com'), ('Proveedor B', '30765432109', 'Avenida Central 456', '9988776655', 'proveedorb@example.com'), ('Proveedor C', '40512378902', 'Calle Secundaria 789', '6677889900', 'proveedorc@example.com'), ('Proveedor D', '50678912304', 'Calle Principal 234', '5544332211', 'proveerd@example.com'), ('Proveedor E', '60890123405', 'Avenida Norte 567', '4433221100', 'proveedore@example.com');

-- INSERT INTO productos
INSERT INTO productos (descripcion, precio) VALUES ('Producto 1', 25.99),('Producto 2', 39.95), ('Producto 3', 15.50), ('Producto 4', 49.75), ('Producto 5', 10.00);

-- INSERT INTO facturas
INSERT INTO facturas (estado_pago, fecha_emision, punto_venta, numero_comprobante, numero_remito) VALUES (true, '2023-12-01', 1001, 12345, 9876), (false, '2023-11-15', 1002, 54321, 6543), (true, '2023-10-20', 1003, 67890, 7890), (false, '2023-09-05', 1004, 24680, 4321), (true, '2023-08-12', 1005, 13579, 5678);