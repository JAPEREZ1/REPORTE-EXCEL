package com.refla.reportes.service;

import com.refla.reportes.common.utils.ExcelReportUtils;
import com.refla.reportes.entity.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class UserExportToExcelService {
    public void exportUsersToExcel(HttpServletResponse response, List<User> users) throws IOException {
        if (users == null || users.isEmpty()) {
            throw new IllegalArgumentException("No data to export.");
        }

        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users.xlsx";
        response.setHeader(headerKey, headerValue);

        ExcelReportUtils excelReportUtils = new ExcelReportUtils();
        excelReportUtils.generateExcelFromEntity(users, response.getOutputStream());
    }
}

