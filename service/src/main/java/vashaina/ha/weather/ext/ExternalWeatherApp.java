package vashaina.ha.weather.ext;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import vashaina.ha.weather.ext.service.wunderground.WundergroundForecastConverter;

/**
 * This application retrieves weather forecasts from external weather services such 
 * as wunderground.com and weather.com.
 */
@SpringBootApplication
@EnableAutoConfiguration
@Configuration()
@ComponentScan("vashaina.ha.weather")
public class ExternalWeatherApp {

    private static Logger log = LoggerFactory.getLogger(ExternalWeatherApp.class);

    /**
     * Application main entrypoint method.
     * @param args
     */
    public static void main(String[] args) {
        log.info("application boot up");
        SpringApplication.run(ExternalWeatherApp.class, args);
    }

    /*
     ************** Bean definitions ************* 
     */

    /**
     * Setup custom converters in a {@link ConversionService} instance.
     * @return conversion service
     */
    @Bean(name = "conversionService")
    public ConversionService getConversionService() {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        Set<Converter<?, ?>> converters = new HashSet<Converter<?, ?>>();
        converters.add(new WundergroundForecastConverter());
        bean.setConverters(converters); //add converters
        bean.afterPropertiesSet();
        return bean.getObject();
    }

}
