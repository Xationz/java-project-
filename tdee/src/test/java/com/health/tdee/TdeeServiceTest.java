package com.health.tdee;

import com.health.tdee.model.TdeeRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests สำหรับ TdeeService
 */
@DisplayName("TDEE Service Tests")
public class TdeeServiceTest {
    
    private TdeeService tdeeService;
    
    @BeforeEach
    public void setUp() {
        tdeeService = new TdeeService();
    }
    
    // ========== Valid Request Tests ==========
    
    @Test
    @DisplayName("ควรคำนวณ TDEE ถูกต้องสำหรับผู้ชาย")
    public void testCalculateTdeeForMale() {
        // Arrange
        TdeeRequest request = new TdeeRequest("male", 30, 80.0, 180.0, 1.375);
        
        // Act
        double tdee = tdeeService.calculateTdee(request);
        
        // Assert
        assertTrue(tdee > 0, "TDEE ต้องมากกว่า 0");
        assertEquals(2447.5, tdee, 1.0, "TDEE ควรอยู่ในช่วงที่คาดหวัง");
    }
    
    @Test
    @DisplayName("ควรคำนวณ TDEE ถูกต้องสำหรับผู้หญิง")
    public void testCalculateTdeeForFemale() {
        // Arrange
        TdeeRequest request = new TdeeRequest("female", 25, 60.0, 165.0, 1.55);
        
        // Act
        double tdee = tdeeService.calculateTdee(request);
        
        // Assert
        assertTrue(tdee > 0, "TDEE ต้องมากกว่า 0");
        assertTrue(tdee < 3000, "TDEE สำหรับหญิงควรน้อยกว่า 3000");
    }
    
    // ========== Validation Tests ==========
    
    @Test
    @DisplayName("ต้อง throw exception เมื่อ gender ไม่ถูกต้อง")
    public void testThrowExceptionForInvalidGender() {
        // Arrange
        TdeeRequest request = new TdeeRequest("other", 30, 80.0, 180.0, 1.375);
        
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            tdeeService.calculateTdee(request);
        }, "ต้อง throw exception สำหรับ gender ไม่ถูกต้อง");
    }
    
    @Test
    @DisplayName("ต้อง throw exception สำหรับอายุต่ำเกินไป")
    public void testThrowExceptionForAgeTooLow() {
        // Arrange
        TdeeRequest request = new TdeeRequest("male", 10, 80.0, 180.0, 1.375);
        
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            tdeeService.calculateTdee(request);
        }, "ต้อง throw exception เมื่ออายุต่ำกว่า 15");
    }
    
    @Test
    @DisplayName("ต้อง throw exception สำหรับอายุสูงเกินไป")
    public void testThrowExceptionForAgeTooHigh() {
        // Arrange
        TdeeRequest request = new TdeeRequest("male", 150, 80.0, 180.0, 1.375);
        
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            tdeeService.calculateTdee(request);
        }, "ต้อง throw exception เมื่ออายุสูงกว่า 100");
    }
    
    @Test
    @DisplayName("ต้อง throw exception สำหรับน้ำหนักต่ำเกินไป")
    public void testThrowExceptionForWeightTooLow() {
        // Arrange
        TdeeRequest request = new TdeeRequest("male", 30, 20.0, 180.0, 1.375);
        
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            tdeeService.calculateTdee(request);
        }, "ต้อง throw exception เมื่อน้ำหนักต่ำกว่า 30 กก.");
    }
    
    @Test
    @DisplayName("ต้อง throw exception สำหรับส่วนสูงต่ำเกินไป")
    public void testThrowExceptionForHeightTooLow() {
        // Arrange
        TdeeRequest request = new TdeeRequest("male", 30, 80.0, 50.0, 1.375);
        
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            tdeeService.calculateTdee(request);
        }, "ต้อง throw exception เมื่อส่วนสูงต่ำกว่า 100 ซม.");
    }
    
    @Test
    @DisplayName("ต้อง throw exception สำหรับระดับกิจกรรมต่ำเกินไป")
    public void testThrowExceptionForActivityLevelTooLow() {
        // Arrange
        TdeeRequest request = new TdeeRequest("male", 30, 80.0, 180.0, 1.0);
        
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            tdeeService.calculateTdee(request);
        }, "ต้อง throw exception เมื่อระดับกิจกรรมต่ำกว่า 1.2");
    }
    
    @Test
    @DisplayName("ต้อง throw exception เมื่อ request เป็น null")
    public void testThrowExceptionForNullRequest() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            tdeeService.calculateTdee(null);
        }, "ต้อง throw exception เมื่อ request เป็น null");
    }
}
