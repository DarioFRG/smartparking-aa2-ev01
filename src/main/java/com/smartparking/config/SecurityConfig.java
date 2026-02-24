package com.smartparking.config;

import org.springframework.context.annotation.Configuration;

/**
 * Configuración de seguridad deshabilitada.
 * Spring Security ha sido removido del proyecto.
 * Si necesita seguridad, agregue la dependencia spring-boot-starter-security al pom.xml
 */
@Configuration
public class SecurityConfig {
    // Configuración vacía - Spring Security no está disponible
}