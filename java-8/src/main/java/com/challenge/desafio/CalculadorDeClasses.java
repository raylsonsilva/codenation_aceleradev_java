package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import java.math.BigDecimal;
import com.challenge.interfaces.Calculavel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class CalculadorDeClasses implements Calculavel
{

    public BigDecimal somar(Object fieldsClass)
    {
        BigDecimal resultSumFields = BigDecimal.ZERO;
        for (Field field : fieldsClass.getClass().getDeclaredFields())
        {
            if (field.getType().equals(BigDecimal.class) && field.isAnnotationPresent(Somar.class))
            {
                try
                {
                    field.setAccessible(true);
                    resultSumFields = resultSumFields.add((BigDecimal) field.get(fieldsClass));
                } catch (IllegalAccessException e)
                {
                    return BigDecimal.ZERO;
                }

            }
        }
        return resultSumFields;
    }

    public BigDecimal subtrair(Object fieldsClass)
    {
        BigDecimal resultSubtractFields = BigDecimal.ZERO;
        for (Field field : fieldsClass.getClass().getDeclaredFields())
        {
            if (field.getType().equals(BigDecimal.class) && field.isAnnotationPresent(Subtrair.class))
            {
                try
                {
                    field.setAccessible(true);
                    resultSubtractFields = resultSubtractFields.add((BigDecimal) field.get(fieldsClass));
                } catch (IllegalAccessException e)
                {
                    return BigDecimal.ZERO;
                }

            }
        }
        return resultSubtractFields;
    }

    public BigDecimal totalizar(Object fieldsClass)
    {
        return somar(fieldsClass).subtract(subtrair(fieldsClass));
    }
}
