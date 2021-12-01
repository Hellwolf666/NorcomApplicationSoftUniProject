package com.example.norcomapllication.config;

import com.example.norcomapllication.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends GlobalMethodSecurityConfiguration {

  private NorcomMethodSecurityExpressionHandler norcomMethodSecurityExpressionHandler;

  @Override
  protected MethodSecurityExpressionHandler createExpressionHandler() {
    return norcomMethodSecurityExpressionHandler;
  }

  @Bean
  public NorcomMethodSecurityExpressionHandler createExpressionHandler(DeviceService deviceService) {
    return new NorcomMethodSecurityExpressionHandler(deviceService);
  }
}

