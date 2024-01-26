package com.DiegoEvangeliste.ControlFacturasProveedores.dto;

import org.springframework.web.multipart.MultipartFile;

public record EmailFileDto(String[] toUsers, String subject, String message, MultipartFile file) { }
