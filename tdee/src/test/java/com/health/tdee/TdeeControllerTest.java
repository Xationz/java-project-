package com.health.tdee;

import com.health.tdee.model.TdeeRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests สำหรับ TdeeController
 */
@SpringBootTest
@DisplayName("TDEE Controller Tests")
public class TdeeControllerTest {
    
    @Test
    @DisplayName("ควรสร้าง TdeeRequest object ได้ถูกต้อง")
    public void testTdeeRequestCreation() {
        // Arrange & Act
        TdeeRequest request = new TdeeRequest("male", 30, 80.0, 180.0, 1.375);
        
        // Assert
        assertEquals("male", request.getGender());
        assertEquals(30, request.getAge());
        assertEquals(80.0, request.getWeight());
        assertEquals(180.0, request.getHeight());
        assertEquals(1.375, request.getActivityLevel());
    }
    
    @Test
    @DisplayName("ควรตั้งค่า TdeeRequest ผ่าน setters ได้")
    public void testTdeeRequestSetters() {
        // Arrange
        TdeeRequest request = new TdeeRequest();
        
        // Act
        request.setGender("female");
        request.setAge(25);
        request.setWeight(65.0);
        request.setHeight(165.0);
        request.setActivityLevel(1.4);
        
        // Assert
        assertEquals("female", request.getGender());
        assertEquals(25, request.getAge());
        assertEquals(65.0, request.getWeight());
        assertEquals(165.0, request.getHeight());
        assertEquals(1.4, request.getActivityLevel());
    }
    
    @Test
    @DisplayName("ควรสร้าง TdeeController instance ได้")
    public void testTdeeControllerInstantiation() {
        // Act
        TdeeController controller = new TdeeController();
        
        // Assert
        assertNotNull(controller);
    }
}


