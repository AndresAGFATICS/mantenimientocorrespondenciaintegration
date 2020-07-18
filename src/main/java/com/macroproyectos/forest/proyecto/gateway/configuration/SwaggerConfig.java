package com.macroproyectos.forest.proyecto.gateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

import java.util.Collection;
import java.util.ArrayList;

@Configuration
@EnableSwagger2
@SuppressWarnings("rawtypes")

/**
 * Clase de configuracion de swagger API, se debe colocar la informacion correspondiente al proyecto
 * 
 * @author Paola Moreno
 *
 */
public class SwaggerConfig {

	@Bean
	public Docket api() {
		Collection<VendorExtension> vendorExtensions = new ArrayList<>();

        ApiInfo apiInfo = new ApiInfo("Forest Nombre del proyecto", "API para ejecutar los servicios asociados a XXXX",
                "Version 1.0.0", "", new Contact("Automatizaci\u00f3n MacroProyectos", "", null), "", "", vendorExtensions);

        return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false).apiInfo(apiInfo).select()
                .apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
	}
}