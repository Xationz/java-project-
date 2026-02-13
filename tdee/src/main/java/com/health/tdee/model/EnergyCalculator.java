package com.health.tdee.model;

/**
 * Interface สำหรับการคำนวณพลังงาน (Abstraction)
 */
public interface EnergyCalculator {
    
    /**
     * คำนวณ Basal Metabolic Rate (BMR)
     * @param gender เพศ (male/female)
     * @param age อายุ (ปี)
     * @param weight น้ำหนัก (กิโลกรัม)
     * @param height ส่วนสูง (เซนติเมตร)
     * @return BMR (หน่วย แคลอรี่/วัน)
     */
    double calculateBMR(String gender, int age, double weight, double height);
    
    /**
     * คำนวณ Total Daily Energy Expenditure (TDEE)
     * @param bmr Basal Metabolic Rate
     * @param activityLevel ระดับกิจกรรม (1.2-1.9)
     * @return TDEE (หน่วย แคลอรี่/วัน)
     */
    double calculateTDEE(double bmr, double activityLevel);
}
