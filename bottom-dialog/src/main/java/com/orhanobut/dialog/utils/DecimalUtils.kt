package com.orhanobut.dialog.utils

import com.ved.framework.utils.StringUtils
import java.math.BigDecimal
import java.text.DecimalFormat

object DecimalUtils {

    fun decimalFormat(decimal : String?) : String{
        if (decimal.isNullOrEmpty()){
            return "0.00"
        }else{
            DecimalFormat("######0.00").let { df ->
                return try {
                    df.format(StringUtils.parseDouble(decimal))
                } catch (e: Exception) {
                    "0.00"
                }
            }
        }
    }

    fun decimalFormat(a : String?,b : String?) : String{
        if (a.isNullOrEmpty()){
            if (b.isNullOrEmpty()){
                return "0.00"
            }else{
                DecimalFormat("######0.00").let { df ->
                    return try {
                        df.format(BigDecimal(b))
                    } catch (e: Exception) {
                        "0.00"
                    }
                }
            }
        }else{
            if (b.isNullOrEmpty()){
                DecimalFormat("######0.00").let { df ->
                    return try {
                        df.format(BigDecimal(a))
                    } catch (e: Exception) {
                        "0.00"
                    }
                }
            }else{
                DecimalFormat("######0.00").let { df ->
                    return try {
                        df.format(BigDecimal(a).add(BigDecimal(b)))
                    } catch (e: Exception) {
                        BigDecimal(a).add(BigDecimal(b)).toString()
                    }
                }
            }
        }
    }

    fun decimalAddFormat(a : String?,b : String?) : String{
        if (a.isNullOrEmpty()){
            if (b.isNullOrEmpty()){
                return "0.00"
            }else{
                DecimalFormat("").let { df ->
                    return try {
                        df.format(BigDecimal(b))
                    } catch (e: Exception) {
                        b
                    }
                }
            }
        }else{
            if (b.isNullOrEmpty()){
                DecimalFormat("").let { df ->
                    return try {
                        df.format(BigDecimal(a))
                    } catch (e: Exception) {
                        a
                    }
                }
            }else{
                DecimalFormat("").let { df ->
                    return try {
                        df.format(BigDecimal(a).add(BigDecimal(b)))
                    } catch (e: Exception) {
                        BigDecimal(a).add(BigDecimal(b)).toString()
                    }
                }
            }
        }
    }

    /**
     * 两个数乘法
     */
    fun decimalFormatMul(a : String?,b : String?) : String{
        if (a.isNullOrEmpty()){
            if (b.isNullOrEmpty()){
                return "0.00"
            }else{
                DecimalFormat("######0.00").let { df ->
                    return try {
                        df.format(BigDecimal(b))
                    } catch (e: Exception) {
                        b
                    }
                }
            }
        }else{
            if (b.isNullOrEmpty()){
                DecimalFormat("######0.00").let { df ->
                    return try {
                        df.format(BigDecimal(a))
                    } catch (e: Exception) {
                        a
                    }
                }
            }else{
                DecimalFormat("######0.00").let { df ->
                    return try {
                        df.format(BigDecimal(a).multiply(BigDecimal(b)))
                    } catch (e: Exception) {
                        BigDecimal(a).multiply(BigDecimal(b)).toString()
                    }
                }
            }
        }
    }

    /**
     * 两个数减法
     */
    fun decimalFormatSubtract(a : String?,b : String?) : String{
        if (a.isNullOrEmpty()){
            if (b.isNullOrEmpty()){
                return "0.00"
            }else{
                DecimalFormat("######0.00").let { df ->
                    return try {
                        df.format(BigDecimal(b))
                    } catch (e: Exception) {
                        b
                    }
                }
            }
        }else{
            if (b.isNullOrEmpty()){
                DecimalFormat("######0.00").let { df ->
                    return try {
                        df.format(BigDecimal(a))
                    } catch (e: Exception) {
                        a
                    }
                }
            }else{
                DecimalFormat("######0.00").let { df ->
                    return try {
                        df.format(BigDecimal(a).subtract(BigDecimal(b)))
                    } catch (e: Exception) {
                        BigDecimal(a).subtract(BigDecimal(b)).toString()
                    }
                }
            }
        }
    }
}