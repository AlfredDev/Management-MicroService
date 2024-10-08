package com.alfred.tracking.trackingservice.controller;

import com.alfred.tracking.trackingservice.Dto.ActivityLogRequest;
import com.alfred.tracking.trackingservice.service.ActivityLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/activities")
@RequiredArgsConstructor
public class ActivityLogController {
    private final ActivityLogService service;


    @PostMapping()
    public ResponseEntity<Integer> createActivityLog(
            @Valid @RequestBody ActivityLogRequest request
    ) {
        return ResponseEntity.ok().body(service.createActivityLog(request));
    }

}
