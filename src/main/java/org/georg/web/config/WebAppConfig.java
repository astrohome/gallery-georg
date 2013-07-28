package org.georg.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(basePackages = {"org.georg.web"})
@ImportResource(value = {"/WEB-INF/application-security.xml"})
public class WebAppConfig {

}
