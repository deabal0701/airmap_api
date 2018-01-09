
package com.kt.airmap.config;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.paths.RelativeSwaggerPathProvider;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

@Configuration
@EnableSwagger
public class SwaggerConfiguration implements ServletContextAware {

    private SpringSwaggerConfig springSwaggerConfig;

    private ServletContext servletContext;

    public static final String[] DEFAULT_INCLUDE_PATTERN = {"/.*/v1.0/.*"};

    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        RelativeSwaggerPathProvider relativeSwaggerPathProvider = new RelativeSwaggerPathProvider(servletContext);
        relativeSwaggerPathProvider.setApiResourcePrefix("airmapapi");
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(apiInfo())
                .pathProvider(relativeSwaggerPathProvider).includePatterns(DEFAULT_INCLUDE_PATTERN);
    }

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo(
			"AirMap API",
			"AirMap 정보",
			"",
			"",
			"",
			""
			);
		return apiInfo;
	}

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}