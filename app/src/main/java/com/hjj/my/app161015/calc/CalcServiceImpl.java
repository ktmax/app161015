package com.hjj.my.app161015.calc;

/**
 * Created by 1027 on 2016-09-24.
 */

public class CalcServiceImpl implements CalcService{

    @Override
    public CalcDTO plus(CalcDTO cal) {
        return null;
    }

    @Override
    public CalcDTO minus(CalcDTO cal) {
        int num1 = cal.getNum1();
        int num2 = cal.getNum2();
        int result = num1 - num2;
        cal.setResult(result);
        return cal;
    }

    @Override
    public CalcDTO multi(CalcDTO cal) {
        return null;
    }

    @Override
    public CalcDTO divid(CalcDTO cal) {
        return null;
    }

    @Override
    public CalcDTO remainder(CalcDTO cal) {
        return null;
    }
}
