package com.yi.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;

/**
 * 自定义validate
 *
 * @author : xiao on 2022/9/13 22:32
 * @version : 1.0
 */
public class ListValueConstraintValidator implements ConstraintValidator<ListValue, Integer> {

    private HashSet<Integer> set = new HashSet<>();

    @Override
    public void initialize(ListValue listValue) {
        int[] value = listValue.value();
        for (int i : value) {
            set.add(i);
        }
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return set.contains(value);
    }
}
