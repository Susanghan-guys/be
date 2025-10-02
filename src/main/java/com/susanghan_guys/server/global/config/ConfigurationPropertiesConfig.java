package com.susanghan_guys.server.global.config;

import com.susanghan_guys.server.oauth2.domain.proterties.RedirectProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = RedirectProperties.class)
public class ConfigurationPropertiesConfig {
}
