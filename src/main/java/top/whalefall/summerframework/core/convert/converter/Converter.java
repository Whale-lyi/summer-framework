package top.whalefall.summerframework.core.convert.converter;

/**
 * @author Liu Yu
 * @description 类型转换抽象接口
 * @date 2025-02-09 13:38:23
 */
public interface Converter<S, T> {

    /**
     * 类型转换
     */
    T convert(S source);

}
