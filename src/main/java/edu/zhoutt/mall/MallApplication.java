package edu.zhoutt.mall;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.zhoutt.mall.configuration.LoginInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@EnableSwagger2
@EnableScheduling
@SpringBootApplication
public class MallApplication extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        SpringApplication.run(MallApplication.class, args);
    }

    // 添加拦截器，拦截所有的请求，并释放特殊请求
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns(excludePathPatterns());
    }

    /**
     * 所有忽略的 url 规则
     */
    private String[] excludePathPatterns() {
        List<String> excludes = new ArrayList<>();

        excludes.add("/api/user/register");
        excludes.add("/api/user/check/*");
        excludes.add("/api/user/login");
        excludes.add("/api/category/get");
        excludes.add("/api/product/get/single/*");
        excludes.add("/api/product/get/category/**");
        excludes.add("/api/product/file/download");
        excludes.add("/api/hot/get/**");

        return excludes.toArray(new String[0]);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/");
        super.addResourceHandlers(registry);
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        builder.locale(Locale.CHINA);
        builder.simpleDateFormat("yyyy-MM-dd HH:mm:ss");
        builder.timeZone(TimeZone.getDefault());
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
        super.configureMessageConverters(converters);
    }

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfo("RestApi", "测试系统", "1.0", "127.0.0.1",
                        new Contact("", "", ""), "", "", Collections.emptyList()));
    }

}
