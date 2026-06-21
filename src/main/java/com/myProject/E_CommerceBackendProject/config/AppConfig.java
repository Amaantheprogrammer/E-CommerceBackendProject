// Meant for password encoding
package com.myProject.E_CommerceBackendProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration // Used to define a class as a source of bean definitions
public class AppConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

/* 
You use a @Configuration class when you need to configure third-party libraries (like ModelMapper, Jackson, or Security filters) 
where you cannot open the source code to add a @Component annotation directly onto their classes.
*/

