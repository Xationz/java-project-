package com.health.tdee.model;

/**
 * Abstract class สำหรับการคำนวณพลังงาน (Inheritance)
 */
public abstract class BaseTdeeCalculator implements EnergyCalculator {
    
    /**
     * Mifflin-St Jeor Formula สำหรับคำนวณ BMR
     * @param gender เพศ (male/female)
     * @param age อายุ
     * @param weight น้ำหนัก
     * @param height ส่วนสูง
     * @return BMR
     */
    @Override
    public double calculateBMR(String gender, int age, double weight, double height) {
        double bmr;
        
        if (gender.equalsIgnoreCase("male")) {
            bmr = (10 * weight) + (6.25 * height) - (5 * age) + 5;
        } else {
            bmr = (10 * weight) + (6.25 * height) - (5 * age) - 161;
        }
        
        return validateBMR(bmr);
    }
    
    /**
     * คำนวณ TDEE จาก BMR และระดับกิจกรรม
     * @param bmr Basal Metabolic Rate
     * @param activityLevel ระดับกิจกรรม
     * @return TDEE
     */
    @Override
    public double calculateTDEE(double bmr, double activityLevel) {
        double tdee = bmr * activityLevel;
        return validateTDEE(tdee);
    }
    
    /**
     * ตรวจสอบความถูกต้องของ BMR (Abstract method)
     * @param bmr ค่า BMR
     * @return BMR ที่ถูกต้อง
     */
    protected abstract double validateBMR(double bmr);
    
    /**
     * ตรวจสอบความถูกต้องของ TDEE (Abstract method)
     * @param tdee ค่า TDEE
     * @return TDEE ที่ถูกต้อง
     */
    protected abstract double validateTDEE(double tdee);
}
