package com.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration //sử dụng dc tính năng javaConfig
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")//bật tính năng
public class JpaAuditingConfig {//thư viện hỗ trợ createdby,  modifiedby, modifieddate...thông qua các nanotation @...
	
	@Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }

    public static class AuditorAwareImpl implements AuditorAware<String> {//hàm kiểm tra ta đã đăng nhập chưa và trả ra người đăng nhập
        @Override
        public String getCurrentAuditor() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) {
                return null;
            }
            return authentication.getName();//tra ra người đăng nhập createdBy, modifiedBy
        }
    }
}