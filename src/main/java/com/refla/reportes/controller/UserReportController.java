package com.refla.reportes.controller;

import com.refla.reportes.dto.UserDTO;
import com.refla.reportes.entity.User;
import com.refla.reportes.service.UserExportToExcelService;
import com.refla.reportes.service.UserReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/report")
public class UserReportController {

    private final UserReportService userReportService;
    private final UserExportToExcelService userExportToExcelService;

    @Autowired
    public UserReportController(UserReportService userReportService,
                                UserExportToExcelService userExportToExcelService) {
        this.userReportService = userReportService;
        this.userExportToExcelService = userExportToExcelService;
    }

    @GetMapping("/export-users")
    public void exportUsersToExcel(HttpServletResponse response) {
        try {List<User> users = userReportService.getAllUsers();
            userExportToExcelService.exportUsersToExcel(response, users);
        } catch (IOException e) {
            throw new RuntimeException("Error al exportar los usuarios", e);
        }
    }
}


