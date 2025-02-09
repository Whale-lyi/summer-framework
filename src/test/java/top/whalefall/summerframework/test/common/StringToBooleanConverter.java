package top.whalefall.summerframework.test.common;

import top.whalefall.summerframework.core.convert.converter.GenericConverter;

import java.util.Collections;
import java.util.Set;

/**
 * @author Liu Yu
 * @date 2025/2/9
 */
public class StringToBooleanConverter implements GenericConverter {

	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		return Collections.singleton(new ConvertiblePair(String.class, Boolean.class));
	}

	@Override
	public Object convert(Object source, Class<?> sourceType, Class<?> targetType) {
		return Boolean.valueOf((String) source);
	}

}
