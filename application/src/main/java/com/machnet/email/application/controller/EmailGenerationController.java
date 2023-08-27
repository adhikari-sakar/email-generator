package com.machnet.email.application.controller;

import com.machnet.domain.email.EmailProviderType;
import com.machnet.email.application.model.EmailResponse;
import com.machnet.email.application.service.EmailGeneratorService;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/email")
@AllArgsConstructor
public class EmailGenerationController {

    private final EmailGeneratorService service;

    @GetMapping("/generate/{type}")
    public ResponseEntity<EmailResponse> generate(@NotNull @PathVariable(name = "type") String providerType) {
        return ResponseEntity.ok(service.generateEmail(EmailProviderType.getType(providerType)));
    }
}
