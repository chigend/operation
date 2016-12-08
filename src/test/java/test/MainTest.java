package test;

import com.cookabuy.entity.dto.input.ReplaceRecommendForm;

/**
 * 2016/12/8
 */
public class MainTest {
    public static void main(String[] args) {
        Class<?> clazz = ReplaceRecommendForm.class;
        boolean flag = clazz.isAssignableFrom(ReplaceRecommendForm.class);
        System.out.print(flag);
    }
}
