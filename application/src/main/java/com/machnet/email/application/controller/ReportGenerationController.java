package com.machnet.email.application.controller;

import com.machnet.email.application.component.ReportGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/report")
@AllArgsConstructor
public class ReportGenerationController {

    private final ReportGenerator reportGenerator;

    @PostMapping("/generate")
    public ResponseEntity<String> generateReport() {
        reportGenerator.generate();
        return ResponseEntity.accepted().body("Report generation completed.");
    }
}
