package com.dominionconsulting.tito.opp.common.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.builders.ApiInfoBuilder;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration
{
	private final Logger logger = LoggerFactory.getLogger(SwaggerConfiguration.class);
	
	@Value("${tito.swagger.title}")
	String title;
	
	@Value("${tito.swagger.description}")
	String	description;
	
	@Value("${tito.swagger.termsOfServiceUrl}")
	String	termsOfServiceUr;
		
	@Value("${tito.swagger.contact}")
	String	contact;
	
	@Value("${tito.swagger.license}")
	String	license;
	
	@Value("${tito.swagger.licenseUrl}")
	String	licenseUrl;
		
	@Value("${tito.swagger.version}")
	String 	version;
		
	@Bean
	public Docket postsApi() 
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("tito-opp-api")
				.apiInfo(apiInfo())
				.select()
				.paths(postPaths())
				.build();
	}
	
	private Predicate<String> postPaths() 
	{
		return or(regex("/opps.*"), regex("/api/.*"));
	}

	private ApiInfo apiInfo() 
	{
		return new ApiInfoBuilder()
				.title(title)
				.description("REST API for TITO Opportunities")
				.termsOfServiceUrl(termsOfServiceUr)
				.contact(contact)
				.license(license)
				.licenseUrl(licenseUrl)
				.version(version)
				.build();
	}
}
