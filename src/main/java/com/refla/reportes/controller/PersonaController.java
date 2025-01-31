//package com.refla.reportes.controller;
//import com.refla.reportes.entity.Persona;
//import com.refla.reportes.service.PersonaExportService;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//@RestController
//public class PersonaController {
//
//    private final PersonaExportService personaExportService;
//
//    public PersonaController(PersonaExportService personaExportService) {
//        this.personaExportService = personaExportService;
//    }
//
//    @GetMapping("/export/personas")
//    public void exportPersonasToExcelWithFilters(
//            HttpServletResponse response,
//            HttpServletRequest request,
//            @RequestParam(required = false) String nombres,
//            @RequestParam(required = false) String identificacion,
//            @RequestParam(required = false) String startDate,
//            @RequestParam(required = false) String endDate
//    ) throws IOException {
//        String requestUrl = request.getRequestURL().toString();
//        personaExportService.exportPersonasToExcelWithFilters(response, nombres, identificacion, startDate, endDate, requestUrl);
//    }
//}

package com.refla.reportes.controller;

import com.refla.reportes.entity.Persona;
import com.refla.reportes.service.PersonaExportService;
import com.refla.reportes.service.PersonaCombinarExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class PersonaController {

    private final PersonaExportService personaExportService;
    private final PersonaCombinarExportService personaCombinarExportService;

    @Autowired
    public PersonaController(PersonaExportService personaExportService, PersonaCombinarExportService personaCombinarExportService) {
        this.personaExportService = personaExportService;
        this.personaCombinarExportService = personaCombinarExportService;
    }

    @GetMapping("/export/personas")
    public void exportPersonasToExcelWithFilters(
            HttpServletResponse response,
            HttpServletRequest request, // Agregar HttpServletRequest aquí
            @RequestParam(required = false) String nombres,
            @RequestParam(required = false) String identificacion,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate
    ) throws IOException {
        String requestUrl = request.getRequestURL().toString(); // Obtener la URL de la solicitud
        personaExportService.exportPersonasToExcelWithFilters(response, nombres, identificacion, startDate, endDate, requestUrl);
    }

    @GetMapping("/export/personas/merged")
    public void exportPersonasWithMergedHeader(
            HttpServletResponse response,
            @RequestParam(required = false) String header,
            @RequestParam(required = false) int[] columnsToMerge
    ) throws IOException {
        List<Persona> personas = personaExportService.getAllPersonas();
        if (header == null || header.trim().isEmpty()) {
            header = "Personas";
        }
        personaCombinarExportService.exportPersonasWithMergedHeader(response, personas, header, columnsToMerge);
    }
}