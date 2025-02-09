package top.whalefall.summerframework.core.convert.converter;

/**
 * @author Liu Yu
 * @description 类型转换工厂
 * @date 2025-02-09 13:39:06
 */
public interface ConverterFactory<S, R> {

    <T extends R> Converter<S, T> getConverter(Class<T> targetType);

}
