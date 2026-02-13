package com.health.tdee.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests สำหรับ MifflinStJeorCalculator
 */
@DisplayName("MifflinStJeor Calculator Tests")
public class MifflinStJeorCalculatorTest {
    
    private MifflinStJeorCalculator calculator;
    
    @BeforeEach
    public void setUp() {
        calculator = new MifflinStJeorCalculator();
    }
    
    // ========== BMR Calculation Tests ==========
    
    @Test
    @DisplayName("ควรคำนวณ BMR ถูกต้องสำหรับชาย")
    public void testCalculateBMRForMale() {
        // Arrange: ชาย อายุ 30 น้ำหนัก 80 กก ส่วนสูง 180 ซม
        // Expected BMR = (10*80) + (6.25*180) - (5*30) + 5 = 800 + 1125 - 150 + 5 = 1780
        
        // Act
        double bmr = calculator.calculateBMR("male", 30, 80, 180);
        
        // Assert
        assertEquals(1780.0, bmr, 0.1, "BMR สำหรับชายหาญควรเท่ากับ 1780");
    }
    
    @Test
    @DisplayName("ควรคำนวณ BMR ถูกต้องสำหรับหญิง")
    public void testCalculateBMRForFemale() {
        // Arrange: หญิง อายุ 25 น้ำหนัก 60 กก ส่วนสูง 165 ซม
        // Expected BMR = (10*60) + (6.25*165) - (5*25) - 161 = 600 + 1031.25 - 125 - 161 = 1345.25
        
        // Act
        double bmr = calculator.calculateBMR("female", 25, 60, 165);
        
        // Assert
        assertEquals(1345.25, bmr, 0.1, "BMR สำหรับหญิงควรเท่ากับ 1345.25");
    }
    
    @Test
    @DisplayName("ต้อง throw exception เมื่อ BMR ต่ำเกินไป")
    public void testThrowExceptionWhenBMRTooLow() {
        // Arrange: ข้อมูลที่ให้ BMR ต่ำสุดเกินไป (weight ต่ำมาก)
        
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateBMR("male", 100, 30, 100);
        }, "ต้อง throw exception เมื่อ BMR ต่ำเกินไป");
    }
    
    // ========== TDEE Calculation Tests ==========
    
    @Test
    @DisplayName("ควรคำนวณ TDEE ถูกต้อง")
    public void testCalculateTDEE() {
        // Arrange: BMR = 1780, Activity Level = 1.375
        // Expected TDEE = 1780 * 1.375 = 2447.5
        
        // Act
        double tdee = calculator.calculateTDEE(1780, 1.375);
        
        // Assert
        assertEquals(2447.5, tdee, 0.1, "TDEE ควรเท่ากับ 2447.5");
    }
    
    @Test
    @DisplayName("ต้อง throw exception เมื่อ TDEE ต่ำเกินไป")
    public void testThrowExceptionWhenTDEETooLow() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateTDEE(400, 1.2); // 400 * 1.2 = 480 < 600 (MIN_TDEE)
        }, "ต้อง throw exception เมื่อ TDEE ต่ำเกินไป");
    }
    
    @Test
    @DisplayName("ต้อง throw exception เมื่อ TDEE สูงเกินไป")
    public void testThrowExceptionWhenTDEETooHigh() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateTDEE(5000, 1.9); // 5000 * 1.9 = 9500 (เกิน max)
        }, "ต้อง throw exception เมื่อ TDEE สูงเกินไป");
    }
    
    // ========== Integration Tests ==========
    
    @Test
    @DisplayName("Full flow: คำนวณ BMR แล้ว TDEE")
    public void testFullCalculationFlow() {
        // Arrange
        double bmr = calculator.calculateBMR("male", 30, 80, 180);
        double activityLevel = 1.55;
        
        // Act
        double tdee = calculator.calculateTDEE(bmr, activityLevel);
        
        // Assert
        assertEquals(2759, tdee, 0.1, "Full flow ควรให้ TDEE ถูกต้อง");
    }
}
