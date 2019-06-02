package ee.moontego.crud.thymeleaf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password,enabled from users where username=?")
                .authoritiesByUsernameQuery("select username, authority from authorities where username=?");
        System.out.println("DNE");
    }

/* In memory user
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password(passwordEncoder()
                .encode("user"))
                .roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder()
                .encode("admin"))
                .roles("ADMIN", "USER");

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/

    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()// We authorize request
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() //we premit static resources, but not html
                //.anyRequest().authenticated() //all requests must be of authenticated user of any role
                .antMatchers("/").hasRole("USER") //All those patterns block REST paths
                .antMatchers("/animals/showFormForAdd").hasRole("ADMIN")
                .antMatchers("/animals/save").hasRole("ADMIN")
                .antMatchers("/animals/showFormForUpdate").hasRole("ADMIN")
                .antMatchers("/animals/delete").hasRole("ADMIN")
                .antMatchers("/users").hasRole("USER")
                .and()
                .formLogin() //we are customizing form process
                .loginPage("/login") //Show custom form at the request mapping
                .loginProcessingUrl("/authenticate") //Login form should POST data to this URL for processing
                .permitAll().and() //everyone can access login form
                .logout().permitAll() //enables logout support for this app
                .and().exceptionHandling().accessDeniedPage("/access-denied"); //Access denied path page
        super.configure(http);
    }
}
