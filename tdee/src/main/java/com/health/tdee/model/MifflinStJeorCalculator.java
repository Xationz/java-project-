package com.health.tdee.model;

/**
 * Concrete implementation ของ BaseTdeeCalculator (Inheritance + Polymorphism)
 */
public class MifflinStJeorCalculator extends BaseTdeeCalculator {
    
    private static final double MIN_BMR = 500.0;
    private static final double MAX_BMR = 5000.0;
    private static final double MIN_TDEE = 600.0;
    private static final double MAX_TDEE = 6000.0;
    
    /**
     * ตรวจสอบความถูกต้องของ BMR
     * @param bmr ค่า BMR
     * @return BMR ที่ถูกต้อง
     */
    @Override
    protected double validateBMR(double bmr) {
        if (bmr < MIN_BMR) {
            throw new IllegalArgumentException("BMR ต่ำเกินไป: " + bmr);
        }
        if (bmr > MAX_BMR) {
            throw new IllegalArgumentException("BMR สูงเกินไป: " + bmr);
        }
        return bmr;
    }
    
    /**
     * ตรวจสอบความถูกต้องของ TDEE
     * @param tdee ค่า TDEE
     * @return TDEE ที่ถูกต้อง
     */
    @Override
    protected double validateTDEE(double tdee) {
        if (tdee < MIN_TDEE) {
            throw new IllegalArgumentException("TDEE ต่ำเกินไป: " + tdee);
        }
        if (tdee > MAX_TDEE) {
            throw new IllegalArgumentException("TDEE สูงเกินไป: " + tdee);
        }
        return tdee;
    }
}
