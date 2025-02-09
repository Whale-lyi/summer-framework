package top.whalefall.summerframework.context.support;

import top.whalefall.summerframework.beans.BeansException;
import top.whalefall.summerframework.beans.factory.FactoryBean;
import top.whalefall.summerframework.beans.factory.InitializingBean;
import top.whalefall.summerframework.core.convert.ConversionService;
import top.whalefall.summerframework.core.convert.converter.Converter;
import top.whalefall.summerframework.core.convert.converter.ConverterFactory;
import top.whalefall.summerframework.core.convert.converter.ConverterRegistry;
import top.whalefall.summerframework.core.convert.converter.GenericConverter;
import top.whalefall.summerframework.core.convert.support.DefaultConversionService;
import top.whalefall.summerframework.core.convert.support.GenericConversionService;

import java.util.Set;

/**
 * @author Liu Yu
 * @description
 * @date 2025-02-09 21:55:50
 */
public class ConversionServiceFactoryBean implements FactoryBean<ConversionService>, InitializingBean {

    private Set<?> converters;

    private GenericConversionService conversionService;

    @Override
    public ConversionService getObject() throws Exception {
        return conversionService;
    }

    public void setConverters(Set<?> converters) {
        this.converters = converters;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws BeansException {
        conversionService = new DefaultConversionService();
        registerConverters(converters, conversionService);
    }

    private void registerConverters(Set<?> converters, ConverterRegistry registry) {
        if (converters != null) {
            for (Object converter : converters) {
                if (converter instanceof GenericConverter) {
                    registry.addConverter((GenericConverter) converter);
                } else if (converter instanceof Converter<?, ?>) {
                    registry.addConverter((Converter<?, ?>) converter);
                } else if (converter instanceof ConverterFactory<?, ?>) {
                    registry.addConverterFactory((ConverterFactory<?, ?>) converter);
                } else {
                    throw new IllegalArgumentException("Each converter object must implement one of the " +
                            "Converter, ConverterFactory, or GenericConverter interfaces");
                }
            }
        }
    }
}
