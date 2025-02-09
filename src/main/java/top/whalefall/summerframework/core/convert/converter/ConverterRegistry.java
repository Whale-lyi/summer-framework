package top.whalefall.summerframework.core.convert.converter;

/**
 * @author Liu Yu
 * @description 类型转换器注册接口
 * @date 2025-02-09 13:39:51
 */
public interface ConverterRegistry {

    void addConverter(Converter<?, ?> converter);

    void addConverterFactory(ConverterFactory<?, ?> converterFactory);

    void addConverter(GenericConverter converter);

}
