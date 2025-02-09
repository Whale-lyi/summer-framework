package top.whalefall.summerframework.test.common;


import top.whalefall.summerframework.core.convert.converter.Converter;

/**
 * @author Liu Yu
 * @date 2025/2/9
 */
public class StringToIntegerConverter implements Converter<String, Integer> {

	@Override
	public Integer convert(String source) {
		return Integer.valueOf(source);
	}

}
