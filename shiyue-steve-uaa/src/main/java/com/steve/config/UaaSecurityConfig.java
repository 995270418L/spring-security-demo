package com.steve.config;

        import com.steve.config.filter.UaaAuthenticationFilter;
        import com.steve.config.jwt.UaaTokenEnhancer;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.autoconfigure.AutoConfigureBefore;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
        import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
        import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
        import org.springframework.security.oauth2.provider.token.TokenEnhancer;
        import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author: steve
 * @Date: Created in 0:14 2018/1/29
 * @Description:
 * @Modified By:
 */
@Configuration
@EnableWebSecurity
@AutoConfigureBefore({UaaAuthorizationServerConfig.class,UaaResourceServerConfig.class})
public class UaaSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UaaAuthenticationFilter jwtAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/oauth/**").permitAll().anyRequest().authenticated().and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();
    }

    @Bean
    public TokenEnhancer tokenEnhancer(){
        return new UaaTokenEnhancer();
    }

}
