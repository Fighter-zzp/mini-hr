package tk.mybatis.mapper;

import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

/**
 * Mapper 的工具类
 * <p>
 * //TODO
 * MapperUtils.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v1.0.0
 * @date 2020/7/27 22.22
 * @see MapperUtils
 **/
public class MapperUtils {
    /**
     * Mapper中添加where后的赋值
     *
     * @param tClass 反射类型
     * @param args   参数 偶数
     * @param <T>    泛型对象 主要生产Example
     * @return {@link Example}
     */
    public static <T> Example whereArgs(Class<T> tClass, String... args) {
        var example = new Example(tClass);
        if (args.length == 0) {
            return example;
        }
        var criteria = example.createCriteria();
        Assert.isTrue(args.length % 2 == 0, "参数数目必须是偶数");
        for (var i = 0; i < args.length - 1; i += 2) {
            criteria = criteria.andEqualTo(args[i], args[i + 1]);
        }
        return example;
    }

    /**
     * 1: 多个sql `and xxx=yyy`
     * 2: 多个sql `and xxx!=yyy`
     *
     * @param example .
     * @param isNot   判断是否是“！=”
     * @param args    .(string)
     * @return .
     */
    public static Example.Criteria andEqualOrNo(Example example, boolean isNot, String... args) {
        if (isNot) {
            return whereCondition(example.createCriteria(),
                    (criteria, arsIndex, arg) -> criteria.andNotEqualTo(arg[0], arg[1]), args);
        } else {
            return whereCondition(example.createCriteria(),
                    (criteria, arsIndex, arg) -> criteria.andEqualTo(arg[0], arg[1]), args);
        }
    }

    /**
     * 多个sql `or xxx=yyy`
     * 多个sql `or xxx!=yyy`
     *
     * @param example .
     * @param isNot   判断是否是“！=”
     * @param args    .(string)
     * @return .
     */
    public static Example.Criteria orEqualOrNo(Example example, boolean isNot, String... args) {
        if (isNot) {
            return whereCondition(example.createCriteria(),
                    (criteria, arsIndex, arg) -> criteria.orNotEqualTo(arg[0], arg[1]), args);
        } else {
            return whereCondition(example.createCriteria(),
                    (criteria, arsIndex, arg) -> criteria.orEqualTo(arg[0], arg[1]), args);
        }
    }

    private interface OptCriteria {
        /**
         * where条件操作
         *
         * @param criteria .
         * @param arsIndex 参数索引
         * @param args     参数数组
         * @return .
         */
        Example.Criteria whereOpt(Example.Criteria criteria, int arsIndex, String... args);
    }

    private static Example.Criteria whereCondition(Example.Criteria criteria, OptCriteria optCriteria, String... args) {
        if (args.length == 0) {
            return criteria;
        }
        Assert.isTrue(args.length % 2 == 0, "参数数目必须是偶数");
        for (var i = 0; i < args.length - 1; i += 2) {
            criteria = optCriteria.whereOpt(criteria, i, args);
        }
        return criteria;
    }

}
