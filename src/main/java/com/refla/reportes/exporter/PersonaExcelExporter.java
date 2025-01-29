package com.refla.reportes.exporter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PersonaExcelExporter {

    private XSSFWorkbook workbook;
    private Sheet sheet;

    public PersonaExcelExporter() {
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine(List<String> headers) {
        sheet = workbook.createSheet("Personas");

        Row headerRow = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        XSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(font);

        for (int i = 0; i < headers.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers.get(i));
            cell.setCellStyle(headerStyle);
        }
    }

    private void writeDataLines(List<List<Object>> data) {
        int rowCount = 1;
        CellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setWrapText(true);
        dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        for (List<Object> rowData : data) {
            Row row = sheet.createRow(rowCount++);

            for (int i = 0; i < rowData.size(); i++) {
                Cell cell = row.createCell(i);
                Object value = rowData.get(i);

                if (value instanceof String) {
                    cell.setCellValue((String) value);
                } else if (value instanceof Number) {
                    cell.setCellValue(((Number) value).doubleValue());
                }

                cell.setCellStyle(dataStyle);
            }
        }
        for (int i = 0; i < data.get(0).size(); i++) {
            sheet.autoSizeColumn(i);
        }
    }
    public void export(HttpServletResponse response, List<String> headers, List<List<Object>> data) throws IOException {
        writeHeaderLine(headers);
        writeDataLines(data);

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
