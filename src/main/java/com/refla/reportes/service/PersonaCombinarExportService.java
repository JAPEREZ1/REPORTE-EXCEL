package com.refla.reportes.service;

import com.refla.reportes.entity.Persona;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class PersonaCombinarExportService {

    public void exportPersonasWithMergedHeader(HttpServletResponse response, List<Persona> personas, String header, int[] columnsToMerge) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=personas_con_celdas_combinadas.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Personas");

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerFont.setFontHeightInPoints((short) 16);
        headerStyle.setFont(headerFont);

        headerStyle.setAlignment(HorizontalAlignment.CENTER);

        Row headerRow = sheet.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue(header);
        headerCell.setCellStyle(headerStyle);


        if (columnsToMerge.length > 0) {
            sheet.addMergedRegion(new CellRangeAddress(0, 0, columnsToMerge[0], columnsToMerge[columnsToMerge.length - 1]));
        }

        CellStyle subHeaderStyle = workbook.createCellStyle();
        subHeaderStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        subHeaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font subHeaderFont = workbook.createFont();
        subHeaderFont.setBold(true);
        subHeaderStyle.setFont(subHeaderFont);

        Row subHeaderRow = sheet.createRow(1);
        for (int i = 0; i < columnsToMerge.length; i++) {
            Cell cell = subHeaderRow.createCell(columnsToMerge[i]);
            if (i == 0) {
                cell.setCellValue("Nombres");
            } else if (i == 1) {
                cell.setCellValue("Identificación");
            }
            cell.setCellStyle(subHeaderStyle);
        }


        int rowCount = 2;
        for (Persona persona : personas) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(persona.getPerNombres());
            row.createCell(1).setCellValue(persona.getPerIdentificacion());
        }

        sheet.setColumnWidth(0, 45 * 256);
        sheet.setColumnWidth(1, 15 * 256);



        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}