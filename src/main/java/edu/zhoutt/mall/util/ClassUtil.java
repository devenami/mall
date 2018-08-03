package edu.zhoutt.mall.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 类工具
 *
 * @author zhoutt
 * @date 2018/4/13
 */
public class ClassUtil {

    /**
     * 给lfs类中的所有的属性赋值
     * <p>
     * 获取两侧对象相等属性名称、相同属性类型的属性。
     * 并将右侧对象属性内的值复制到左侧对象对应的属性中，
     * 最后返回组装完成的左侧对象
     * </p>
     *
     * @param lfsClass 左边的对象
     * @param rgsClass 右边的对象
     * @param <L>      左边对象的类型，也是该方法返回的对象类型
     * @param <R>      右边的对象类型
     * @return 左边的对象
     */
    public static <L, R> L copy(Class<L> lfsClass, R rgsClass, String... ignores) {
        List<String> ignoreList = new ArrayList<>(Arrays.asList(ignores));
        // 初始化返回；类型
        L retClass = null;
        try {
            retClass = lfsClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        // 实体中所有的属性
        Field[] declaredFields = lfsClass.getDeclaredFields();
        for (Field entityField : declaredFields) {
            // 设置实体属性可访问
            entityField.setAccessible(true);
            // 取实体中属性的名称
            String name = entityField.getName();
            if (ignoreList.contains(name)) {
                continue;
            }
            // 取实体中属性的类型
            Class<?> type = entityField.getType();
            try {
                // vo中属性
                Field voField = rgsClass.getClass().getDeclaredField(name);
                // 设置vo属性可访问
                voField.setAccessible(true);
                // vo中属性的类型
                Class<?> voType = voField.getType();
                if (!type.equals(voType)) {
                    continue;
                }
                // vo中属性的值
                Object voValue = voField.get(rgsClass);
                // 为实体的属性设置值
                entityField.set(retClass, voValue);
            } catch (IllegalAccessException | NoSuchFieldException ignore) {
            }
        }
        return retClass;
    }


}
