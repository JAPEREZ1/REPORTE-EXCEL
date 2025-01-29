package com.refla.reportes.controller;
import com.refla.reportes.service.PersonaExportService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
//            @RequestParam(required = false) String nombres,
//            @RequestParam(required = false) String identificacion,
//            @RequestParam(required = false) String startDate,
//            @RequestParam(required = false) String endDate
//    ) throws IOException {
//        personaExportService.exportPersonasToExcelWithFilters(response, nombres, identificacion, startDate, endDate);
//    }
//}
@RestController
public class PersonaController {

    private final PersonaExportService personaExportService;

    public PersonaController(PersonaExportService personaExportService) {
        this.personaExportService = personaExportService;
    }

    @GetMapping("/export/personas")
    public void exportPersonasToExcelWithFilters(
            HttpServletResponse response,
            HttpServletRequest request,
            @RequestParam(required = false) String nombres,
            @RequestParam(required = false) String identificacion,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate
    ) throws IOException {
        String requestUrl = request.getRequestURL().toString();
        personaExportService.exportPersonasToExcelWithFilters(response, nombres, identificacion, startDate, endDate, requestUrl);
    }
}