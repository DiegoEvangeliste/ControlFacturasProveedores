package com.DiegoEvangeliste.ControlFacturasProveedores.controller;

import com.DiegoEvangeliste.ControlFacturasProveedores.dto.EmailFileDto;
import com.DiegoEvangeliste.ControlFacturasProveedores.dto.EmailSimpleDto;
import com.DiegoEvangeliste.ControlFacturasProveedores.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/email")
public class MailController {

    @Autowired
    private IEmailService emailService;

    @PostMapping("/sendSimpleMessage")
    public ResponseEntity<Map<String, String>> receiveRequestEmail(@RequestBody EmailSimpleDto email) {
        emailService.sendSimpleEmail(email.toUsers(), email.subject(), email.message());

        Map<String, String> response = new HashMap<>();
        response.put("estado", "Enviado");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/sendMessageFile")
    public ResponseEntity<Map<String, String>> receiveRequestEmailWithFile(@ModelAttribute EmailFileDto email) {
        try {
            String fileName = email.file().getOriginalFilename();

            // DEFINIMOS LA RUTA DONDE VAMOS A GUARDAR EL ARCHIVO Y LO GUARDAMOS EN CASO DE QUE NO EXISTA, SI YA EXISTE LO REEMPLAZAMOS
            Path path = Paths.get("src/main/resources/files" + fileName);
            Files.createDirectories(path.getParent());
            Files.copy(email.file().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            File file = path.toFile();
            emailService.sendEmailWithFile(email.toUsers(), email.subject(), email.message(), file);

            Map<String, String> response = new HashMap<>();
            response.put("estado", "Enviado");
            response.put("archivo", fileName);

            return ResponseEntity.ok(response);
        } catch (IOException e) {
            throw new RuntimeException("Error sending email with attachment" + e.getMessage());
        }
    }
}
