package com.refla.reportes.common.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.OutputStream;
import java.util.List;

public class ExcelReportUtils {

    private XSSFWorkbook workbook;
    private Sheet sheet;

    public ExcelReportUtils() {
        this.workbook = new XSSFWorkbook();
    }

    public void generateExcelFromEntity(List<?> entities, OutputStream outputStream) {
        if (entities == null || entities.isEmpty()) {
            throw new IllegalArgumentException("No data available to export.");
        }

        createSheetWithHeaders(entities);

        autoSizeColumns();

        try {
            workbook.write(outputStream);
            workbook.close();
        } catch (Exception e) {
            throw new RuntimeException("Error writing Excel file: " + e.getMessage());
        }
    }

    private void autoSizeColumns() {
        int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();
        for (int i = 0; i < columnCount; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private void createSheetWithHeaders(List<?> entities) {
        sheet = workbook.createSheet("Reporte");

        String[] headers = new String[]{
                "No",
                "Identificacion",
                "Nombres",
                "Tipo Identificacion",
                "Codigo Institucion",
                "Codigo Ubicacion",
                "Listas Control",
                "Peps",
                "Fecha Nacimiento",
                "Sexo",
                "Ingresos",
                "Patrimonio",
                "Estado Informacion",
                "Estado",
                "Tipo Persona",
                "Creado Por",
                "Modificado Por",
                "Eliminado",
                "Creado En",
                "Actualizado En",
                "Codigo Tipo Estado Civil"
        };

        Row headerRow = sheet.createRow(0);
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        XSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(font);

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        int rowCount = 1;
        CellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setWrapText(true);
        dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        for (Object entity : entities) {
            Row row = sheet.createRow(rowCount++);

            row.createCell(0).setCellValue(rowCount - 1); // Número de fila
            row.createCell(1).setCellValue(getFieldValue(entity, "perIdentificacion"));
            row.createCell(2).setCellValue(getFieldValue(entity, "perNombres"));
            row.createCell(3).setCellValue(getFieldValue(entity, "perTipoIdentificacion"));
            row.createCell(4).setCellValue(getFieldValue(entity, "codigoInstitucion"));
            row.createCell(5).setCellValue(getFieldValue(entity, "codigoUbicacion"));
            row.createCell(6).setCellValue(getFieldValue(entity, "perListasControl"));
            row.createCell(7).setCellValue(getFieldValue(entity, "perPeps"));
            row.createCell(8).setCellValue(getFieldValue(entity, "perFechaNacimiento"));
            row.createCell(9).setCellValue(getFieldValue(entity, "perSexo"));
            row.createCell(10).setCellValue(getFieldValue(entity, "perIngresos"));
            row.createCell(11).setCellValue(getFieldValue(entity, "perPatrimonio"));
            row.createCell(12).setCellValue(getFieldValue(entity, "perEstadoInformacion"));
            row.createCell(13).setCellValue(getFieldValue(entity, "perEstado"));
            row.createCell(14).setCellValue(getFieldValue(entity, "perTipoPersona"));
            row.createCell(15).setCellValue(getFieldValue(entity, "audCrea"));
            row.createCell(16).setCellValue(getFieldValue(entity, "audModifica"));
            row.createCell(17).setCellValue(getFieldValue(entity, "audEliminado"));
            row.createCell(18).setCellValue(getFieldValue(entity, "createdAt"));
            row.createCell(19).setCellValue(getFieldValue(entity, "updatedAt"));
            row.createCell(20).setCellValue(getFieldValue(entity, "codigoTipoEstadoCivil"));

            for (int i = 0; i < headers.length; i++) {
                row.getCell(i).setCellStyle(dataStyle);
            }
        }

        autoSizeColumns();
    }
    private String getFieldValue(Object entity, String fieldName) {
        try {
            return (String) entity.getClass().getMethod("get" + capitalizeFirstLetter(fieldName)).invoke(entity);
        } catch (Exception e) {
            return "";
        }
    }
    private String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}