package com.orhanobut.dialog.utils;

import java.math.BigDecimal;
import java.util.List;

public class BigDecimalUtils
{
    private static BigDecimalUtils instance = null;

    /**
     * 构造方法描述:私有构造方法
     */
    private BigDecimalUtils() {
    }


    /**
     * 构造方法描述:获取实例
     *
     * @return 返 回 类 型:Utils
     */
    public synchronized static BigDecimalUtils getInstance() {
        if (instance == null) {
            instance = new BigDecimalUtils();
        }

        return instance;
    }
    /**
     * @return java.math.BigDecimal 总和
     * 示例：BigDecimalUtils.add(参数,参数,参数,参数,...);
     * @Description 加法运算
     * @Param [param] 可变长度数组，把需要计算的数值填进来
     */
    public  BigDecimal add(BigDecimal... param) {
        BigDecimal sumAdd = BigDecimal.valueOf(0);
        for (int i = 0; i < param.length; i++) {
            BigDecimal bigDecimal = param[i] == null ? new BigDecimal(0) : param[i];
            sumAdd = sumAdd.add(bigDecimal);
        }
        return sumAdd;
    }


    public  BigDecimal add(List<String> value)
    {

        if(value!=null &&value.size()>0)
        {
            BigDecimal sumAdd = BigDecimal.valueOf(0);
            for (int i = 0; i <value.size() ; i++)
            {
                sumAdd = sumAdd.add(new BigDecimal(value.get(i)));
            }
            return sumAdd;
        }
        else
        {
            BigDecimal sumAdd = BigDecimal.valueOf(0);
            return sumAdd;
        }
    }


    /**
     * @return java.math.BigDecimal 计算结果
     * 示例：BigDecimalUtils.subtract(被减数,减数,减数,减数,...);
     * @Description 加法运算 如果被减数为null 结果就为0
     * @Param [param] 第一个为被减数 可以传入多个 因为参数是一个可变长度的数组
     */
    public  BigDecimal subtract(BigDecimal... param) {
        BigDecimal sumLess = param[0];//被减数
        if (sumLess == null) return new BigDecimal(0);
        for (int i = 1; i < param.length; i++) {
            BigDecimal bigDecimal = param[i] == null ? new BigDecimal(0) : param[i];
            sumLess = sumLess.subtract(bigDecimal);
        }
        return sumLess;
    }

    /**
     * @return java.math.BigDecimal
     * 计算结果 KeepType[0]保留几位小数点
     * KeepType[1]保留小数点规则：
     * 【BigDecimal.ROUND_HALF_UP四舍五入】
     * 【BigDecimal.ROUND_DOWN 直接删除多余的小数位，如2.35会变成2.3 】
     * 【BigDecimal.ROUND_HALF_DOWN)四舍五入，2.35变成2.3，如果是5则向下舍】
     * @Description 乘法运算 如一方参数为0或者null计算结果为0
     * @Param [first, last]
     *  为了防止精度丢失， last 构造BigDecimal传了string 字符串
     */
    public  BigDecimal multiply(BigDecimal first, BigDecimal last,int... KeepType) {
        if (first == null) first = new BigDecimal(0);
        if (last == null) last = new BigDecimal(0);
        int scale1=2; //默认保留几位数点
        int scale2=BigDecimal.ROUND_HALF_UP;
        if(KeepType.length==1)
        {
            scale1=KeepType[0];
        }
        if(KeepType.length==2)
        {
            scale1=KeepType[0];
            scale2=KeepType[1];
        }
        System.out.println("======scale2:"+scale2);
        return first.multiply(last).setScale(scale1, scale2);
    }

    /**
     * @return java.math.BigDecimal 计算结果 保留小数点后两位 规则为四舍五入
     * @Description 除法运算 如一方参数为0或者null计算结果为0
     * @Param [first, last]
     */
    public  BigDecimal divide(BigDecimal first, BigDecimal last,int... KeepType)
    {
        if (first == null) first = new BigDecimal(0);
        if (last == null) last = new BigDecimal(0);
        int scale1=2; //默认保留几位数点
        int scale2=BigDecimal.ROUND_HALF_UP;
        if(KeepType.length==1)
        {
            scale1=KeepType[0];
        }
        if(KeepType.length==2)
        {
            scale1=KeepType[0];
            scale2=KeepType[1];
        }

        return first.divide(last).setScale(scale1, scale2);
    }

    /**
     * @return flag = -1，表示bigdemical小于bigdemical1。
     *   flag =0，表示bigdemical等于bigdemical1。
     *    flag =1，表示bigdemical大于bigdemical1。
     *
     * 两个字符串比较
     */
    public  int compare(String bg1, String bg2)
    {
        BigDecimal bigDecimal1=new BigDecimal(bg1);
        BigDecimal bigDecimal2=new BigDecimal(bg2);
        int flag = bigDecimal1.compareTo(bigDecimal2);

            /*   flag = -1，表示bigdemical小于bigdemical1。
                    flag =0，表示bigdemical等于bigdemical1。
                    flag =1，表示bigdemical大于bigdemical1。*/
        return flag;
    }






}
