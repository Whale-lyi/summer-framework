package top.whalefall.summerframework.test.ioc;

import org.junit.Test;
import top.whalefall.summerframework.context.support.ClassPathXmlApplicationContext;
import top.whalefall.summerframework.core.convert.converter.Converter;
import top.whalefall.summerframework.core.convert.support.GenericConversionService;
import top.whalefall.summerframework.core.convert.support.StringToNumberConverterFactory;
import top.whalefall.summerframework.test.bean.Car;
import top.whalefall.summerframework.test.common.StringToBooleanConverter;
import top.whalefall.summerframework.test.common.StringToIntegerConverter;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author Liu Yu
 * @date 2025/2/9
 */
public class TypeConversionTest {

	@Test
	public void testConversionService() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_type_conversion.xml");

		Car car = applicationContext.getBean("car", Car.class);
		assertThat(car.getPrice()).isEqualTo(1000000);
		assertThat(car.getProduceDate()).isEqualTo(LocalDate.of(2021, 1, 1));
	}

	@Test
	public void testStringToIntegerConverter() throws Exception {
		StringToIntegerConverter converter = new StringToIntegerConverter();
		Integer num = converter.convert("8888");
		assertThat(num).isEqualTo(8888);
	}

	@Test
	public void testStringToNumberConverterFactory() throws Exception {
		StringToNumberConverterFactory converterFactory = new StringToNumberConverterFactory();

		Converter<String, Integer> stringToIntegerConverter = converterFactory.getConverter(Integer.class);
		Integer intNum = stringToIntegerConverter.convert("8888");
		assertThat(intNum).isEqualTo(8888);

		Converter<String, Long> stringToLongConverter = converterFactory.getConverter(Long.class);
		Long longNum = stringToLongConverter.convert("8888");
		assertThat(longNum).isEqualTo(8888L);
	}

	@Test
	public void testGenericConverter() throws Exception {
		StringToBooleanConverter converter = new StringToBooleanConverter();

		Boolean flag = (Boolean) converter.convert("true", String.class, Boolean.class);
		assertThat(flag).isTrue();
	}

	@Test
	public void testGenericConversionService() throws Exception {
		GenericConversionService conversionService = new GenericConversionService();
		conversionService.addConverter(new StringToIntegerConverter());

		Integer intNum = conversionService.convert("8888", Integer.class);
		assertThat(conversionService.canConvert(String.class, Integer.class)).isTrue();
		assertThat(intNum).isEqualTo(8888);

		conversionService.addConverterFactory(new StringToNumberConverterFactory());
		assertThat(conversionService.canConvert(String.class, Long.class)).isTrue();
		Long longNum = conversionService.convert("8888", Long.class);
		assertThat(longNum).isEqualTo(8888L);

		conversionService.addConverter(new StringToBooleanConverter());
		assertThat(conversionService.canConvert(String.class, Boolean.class)).isTrue();
		Boolean flag = conversionService.convert("true", Boolean.class);
		assertThat(flag).isTrue();
	}
}