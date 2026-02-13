package com.health.tdee;

import com.health.tdee.model.TdeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

/**
 * REST Controller สำหรับ TDEE API
 */
@RestController
@RequestMapping("/api/tdee")
@Validated
public class TdeeController {

    @Autowired
    private TdeeService tdeeService;

    /**
     * POST /api/tdee - คำนวณ TDEE
     * @param request ข้อมูลผู้ใช้
     * @return TDEE
     */
    @PostMapping
    public ResponseEntity<?> calculateTdee(@Valid @RequestBody TdeeRequest request) {
        try {
            double tdee = tdeeService.calculateTdee(request);
            return ResponseEntity.ok(tdee);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    /**
     * GET /api/tdee/health - ตรวจสอบสถานะ API
     * @return สถานะ
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("TDEE API is running");
    }
}
