package top.whalefall.summerframework.core.convert.support;

import top.whalefall.summerframework.core.convert.converter.ConverterRegistry;

/**
 * @author Liu Yu
 * @description
 * @date 2025-02-09 14:26:15
 */
public class DefaultConversionService extends GenericConversionService {

    public DefaultConversionService() {
        addDefaultConverters(this);
    }

    public static void addDefaultConverters(ConverterRegistry converterRegistry) {
        converterRegistry.addConverterFactory(new StringToNumberConverterFactory());
        //TODO 添加其他ConverterFactory
    }

}
