package com.health.tdee;

import com.health.tdee.model.EnergyCalculator;
import com.health.tdee.model.MifflinStJeorCalculator;
import com.health.tdee.model.TdeeRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Service สำหรับการคำนวณ TDEE
 * ใช้ Strategy Pattern เพื่อเลือก Calculator
 */
@Service
@Validated
public class TdeeService {
    
    private final EnergyCalculator calculatorStrategy;
    
    public TdeeService() {
        // ใช้ Mifflin-St Jeor Formula ตามค่าเริ่มต้น
        this.calculatorStrategy = new MifflinStJeorCalculator();
    }
    
    /**
     * คำนวณ TDEE จากข้อมูลผู้ใช้
     * @param request ข้อมูลจากผู้ใช้
     * @return TDEE (หน่วย แคลอรี่/วัน)
     */
    public double calculateTdee(@Valid @NotNull(message = "Request ต้องไม่เป็นค่าว่าง") TdeeRequest request) {
        validateRequest(request);
        
        // Step 1: คำนวณ BMR
        double bmr = calculatorStrategy.calculateBMR(
            request.getGender(),
            request.getAge(),
            request.getWeight(),
            request.getHeight()
        );
        
        // Step 2: คำนวณ TDEE = BMR * Activity Level
        double tdee = calculatorStrategy.calculateTDEE(bmr, request.getActivityLevel());
        
        return tdee;
    }
    
    /**
     * ตรวจสอบความถูกต้องของข้อมูล
     * @param request ข้อมูลจากผู้ใช้
     */
    private void validateRequest(TdeeRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request ต้องไม่เป็นค่าว่าง");
        }
        
        if (request.getGender() == null || 
            (!request.getGender().equalsIgnoreCase("male") && 
             !request.getGender().equalsIgnoreCase("female"))) {
            throw new IllegalArgumentException("เพศต้องเป็น male หรือ female");
        }
        
        if (request.getAge() == null || request.getAge() < 15 || request.getAge() > 100) {
            throw new IllegalArgumentException("อายุต้องอยู่ระหว่าง 15-100 ปี");
        }
        
        if (request.getWeight() == null || request.getWeight() <= 30 || request.getWeight() > 300) {
            throw new IllegalArgumentException("น้ำหนักต้องอยู่ระหว่าง 30-300 กก.");
        }
        
        if (request.getHeight() == null || request.getHeight() <= 100 || request.getHeight() > 250) {
            throw new IllegalArgumentException("ส่วนสูงต้องอยู่ระหว่าง 100-250 ซม.");
        }
        
        if (request.getActivityLevel() == null || 
            request.getActivityLevel() < 1.2 || request.getActivityLevel() > 1.9) {
            throw new IllegalArgumentException("ระดับกิจกรรมต้องอยู่ระหว่าง 1.2-1.9");
        }
    }
}
