package com.health.tdee.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests สำหรับ TdeeRequest Model
 */
@DisplayName("TDEE Request Model Tests")
public class TdeeRequestTest {
    
    private TdeeRequest request;
    
    @BeforeEach
    public void setUp() {
        request = new TdeeRequest();
    }
    
    // ========== Constructor Tests ==========
    
    @Test
    @DisplayName("ควรสร้าง TdeeRequest ด้วย no-arg constructor")
    public void testNoArgConstructor() {
        // Act
        TdeeRequest req = new TdeeRequest();
        
        // Assert
        assertNotNull(req, "ควรสร้าง object ได้");
    }
    
    @Test
    @DisplayName("ควรสร้าง TdeeRequest ด้วย all-args constructor")
    public void testAllArgsConstructor() {
        // Act
        TdeeRequest req = new TdeeRequest("male", 30, 80.0, 180.0, 1.375);
        
        // Assert
        assertEquals("male", req.getGender());
        assertEquals(30, req.getAge());
        assertEquals(80.0, req.getWeight());
        assertEquals(180.0, req.getHeight());
        assertEquals(1.375, req.getActivityLevel());
    }
    
    // ========== Getter/Setter Tests (Encapsulation) ==========
    
    @Test
    @DisplayName("ควรตั้งและดึง gender ได้ถูกต้อง")
    public void testGenderGetterSetter() {
        // Act
        request.setGender("female");
        
        // Assert
        assertEquals("female", request.getGender());
    }
    
    @Test
    @DisplayName("ควรตั้งและดึง age ได้ถูกต้อง")
    public void testAgeGetterSetter() {
        // Act
        request.setAge(25);
        
        // Assert
        assertEquals(25, request.getAge());
    }
    
    @Test
    @DisplayName("ควรตั้งและดึง weight ได้ถูกต้อง")
    public void testWeightGetterSetter() {
        // Act
        request.setWeight(75.5);
        
        // Assert
        assertEquals(75.5, request.getWeight());
    }
    
    @Test
    @DisplayName("ควรตั้งและดึง height ได้ถูกต้อง")
    public void testHeightGetterSetter() {
        // Act
        request.setHeight(170.0);
        
        // Assert
        assertEquals(170.0, request.getHeight());
    }
    
    @Test
    @DisplayName("ควรตั้งและดึง activityLevel ได้ถูกต้อง")
    public void testActivityLevelGetterSetter() {
        // Act
        request.setActivityLevel(1.55);
        
        // Assert
        assertEquals(1.55, request.getActivityLevel());
    }
    
    // ========== Data Integrity Tests ==========
    
    @Test
    @DisplayName("ควรเก็บข้อมูลหลายรายการได้พร้อมกัน")
    public void testMultiplePropertiesAtOnce() {
        // Act
        request.setGender("male");
        request.setAge(35);
        request.setWeight(85.0);
        request.setHeight(175.0);
        request.setActivityLevel(1.725);
        
        // Assert
        assertEquals("male", request.getGender());
        assertEquals(35, request.getAge());
        assertEquals(85.0, request.getWeight());
        assertEquals(175.0, request.getHeight());
        assertEquals(1.725, request.getActivityLevel());
    }
    
    @Test
    @DisplayName("ควรสามารถสร้าง object หลายตัวแต่ละตัวเป็นอิสระ")
    public void testMultipleInstancesAreIndependent() {
        // Arrange
        TdeeRequest req1 = new TdeeRequest("male", 30, 80.0, 180.0, 1.375);
        TdeeRequest req2 = new TdeeRequest("female", 25, 60.0, 165.0, 1.55);
        
        // Act & Assert
        assertNotEquals(req1.getGender(), req2.getGender());
        assertNotEquals(req1.getAge(), req2.getAge());
        assertNotEquals(req1.getWeight(), req2.getWeight());
    }
}
