package com.health.tdee.model;

import jakarta.validation.constraints.*;

/**
 * DTO สำหรับรับข้อมูลจากผู้ใช้
 */
public class TdeeRequest {
    
    @NotNull(message = "เพศต้องไม่เป็นค่าว่าง")
    private String gender;
    
    @NotNull(message = "อายุต้องไม่เป็นค่าว่าง")
    @Min(value = 15, message = "อายุต้องมากกว่าหรือเท่ากับ 15")
    @Max(value = 100, message = "อายุต้องน้อยกว่าหรือเท่ากับ 100")
    private Integer age;
    
    @NotNull(message = "น้ำหนักต้องไม่เป็นค่าว่าง")
    @DecimalMin(value = "30", message = "น้ำหนักต้องมากกว่า 30 กก.")
    @DecimalMax(value = "300", message = "น้ำหนักต้องน้อยกว่า 300 กก.")
    private Double weight;
    
    @NotNull(message = "ส่วนสูงต้องไม่เป็นค่าว่าง")
    @DecimalMin(value = "100", message = "ส่วนสูงต้องมากกว่า 100 ซม.")
    @DecimalMax(value = "250", message = "ส่วนสูงต้องน้อยกว่า 250 ซม.")
    private Double height;
    
    @NotNull(message = "ระดับกิจกรรมต้องไม่เป็นค่าว่าง")
    @DecimalMin(value = "1.2", message = "ระดับกิจกรรมต้องมากกว่า 1.2")
    @DecimalMax(value = "1.9", message = "ระดับกิจกรรมต้องน้อยกว่า 1.9")
    private Double activityLevel;

    // Constructors
    public TdeeRequest() {}

    public TdeeRequest(String gender, Integer age, Double weight, Double height, Double activityLevel) {
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.activityLevel = activityLevel;
    }

    // Getters and Setters (Encapsulation)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(Double activityLevel) {
        this.activityLevel = activityLevel;
    }
}
