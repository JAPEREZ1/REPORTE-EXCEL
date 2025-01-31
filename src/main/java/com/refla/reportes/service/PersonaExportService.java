package com.refla.reportes.service;

import com.refla.reportes.common.utils.ExcelReportUtils;
import com.refla.reportes.dto.PersonaDTO;
import com.refla.reportes.entity.Persona;
import com.refla.reportes.repo.PersonaRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaExportService {

    private final PersonaRepository personaRepository;

    public PersonaExportService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public void exportPersonasToExcelWithFilters(HttpServletResponse response, String nombres, String identificacion, String startDate, String endDate, String requestUrl) throws IOException {
        if ("localhost:8081/export/personas".equals(requestUrl)) {
            List<Persona> personas = personaRepository.findAll();
            if (personas.isEmpty()) {
                throw new IllegalArgumentException("No se encontraron datos.");
            }
            exportToExcel(response, personas);
            return;
        }

//        if (isEmpty(nombres) && isEmpty(identificacion) && isEmpty(startDate) && isEmpty(endDate)) {
//            throw new IllegalArgumentException("No se puede completar el proceso. Al menos un campo debe ser proporcionado.");
//        }

        if (nombres != null && nombres.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo 'nombres' está vacío. Por favor, especifique un valor válido.");
        }
        if (identificacion != null && identificacion.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo 'identificación' está vacío. Por favor, especifique un valor válido.");
        }
        if ((startDate == null || startDate.trim().isEmpty()) ^ (endDate == null || endDate.trim().isEmpty())) {
            throw new IllegalArgumentException("Ambos campos de fecha ('startDate' y 'endDate') deben estar completos para aplicar el filtro de rango.");
        }

        Persona filterPersona = new Persona();
        filterPersona.setPerNombres(nombres);
        filterPersona.setPerIdentificacion(identificacion);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withMatcher("perNombres", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("perIdentificacion", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        Example<Persona> example = Example.of(filterPersona, matcher);
        List<Persona> personas = personaRepository.findAll(example);

        if (!isEmpty(startDate) && !isEmpty(endDate)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);

            personas = personas.stream()
                    .filter(persona -> {
                        if (persona.getUpdatedAt() != null) {
                            LocalDate updatedAtDate = LocalDate.parse(persona.getUpdatedAt().substring(0, 10), formatter);
                            return !updatedAtDate.isBefore(start) && !updatedAtDate.isAfter(end);
                        }
                        return false;
                    })
                    .collect(Collectors.toList());
        }

        if (personas.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron datos con los filtros especificados.");
        }

        exportToExcel(response, personas);
    }

    private void exportToExcel(HttpServletResponse response, List<Persona> personas) throws IOException {
        List<PersonaDTO> personaDTOs = personas.stream().map(persona -> {
            PersonaDTO dto = new PersonaDTO();
            dto.setPerNombres(persona.getPerNombres());
            dto.setCodigoInstitucion(persona.getCodigoInstitucion());
            dto.setCodigoUbicacion(persona.getCodigoUbicacion());
            dto.setPerTipoIdentifiacion(persona.getPerTipoIdentificacion());
            dto.setPerIdentificacion(persona.getPerIdentificacion());
            dto.setPerListasControl(persona.getPerListasControl());
            dto.setPerPeps(persona.getPerPeps());
            dto.setPerFechaNacimiento(persona.getPerFechaNacimiento());
            dto.setPerSexo(persona.getPerSexo());
            dto.setPerIngresos(persona.getPerIngresos());
            dto.setPerPatrimonio(persona.getPerPatrimonio());
            dto.setPerEstadoInformacion(persona.getPerEstadoInformacion());
            dto.setPerEstado(persona.getPerEstado());
            dto.setPerTipoPersona(persona.getPerTipoPersona());
            dto.setAudCrea(persona.getAudCrea());
            dto.setAudModifica(persona.getAudModifica());
            dto.setAudEliminado(persona.getAudEliminado());
            dto.setCreatedAt(persona.getCreatedAt());
            dto.setUpdatedAt(persona.getUpdatedAt());
            dto.setCodigoTipoEstadoCivil(persona.getCodigoTipoEstadoCivil());
            return dto;
        }).collect(Collectors.toList());

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=personas_filtradas.xlsx");
        ExcelReportUtils excelReportUtils = new ExcelReportUtils();
        excelReportUtils.generateExcelFromEntity(personaDTOs, response.getOutputStream());
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }
}
