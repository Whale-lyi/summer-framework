package top.whalefall.summerframework.core.convert;

/**
 * @author Liu Yu
 * @description 类型转换抽象接口
 * @date 2025-02-09 13:37:03
 */
public interface ConversionService {

    boolean canConvert(Class<?> sourceType, Class<?> targetType);

    <T> T convert(Object source, Class<T> targetType);

}
