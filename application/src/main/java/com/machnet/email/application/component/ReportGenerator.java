package com.machnet.email.application.component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.machnet.email.application.repository.ReportEntity;
import com.machnet.email.application.repository.ReportJpaRepository;
import com.machnet.shared.utils.AsyncReportGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
@AllArgsConstructor
public class ReportGenerator {

    private final ReportJpaRepository jpaRepository;

    public void generate() {
        new AsyncReportGenerator().generateAsync(parse(), jpaRepository::saveAll);
    }

    private List<ReportEntity> parse() {
        var mapper = new ObjectMapper();
        var typeReference = new TypeReference<List<ReportEntity>>() {
        };
        var inputStream = getClass().getResourceAsStream("/data.json");
        try {
            return mapper.readValue(inputStream, typeReference);
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException("Failed to read data " + e.getMessage());
        }
    }
}
