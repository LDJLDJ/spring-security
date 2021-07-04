import com.mayikt.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("com.mayikt")
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = { "com.mayikt.repository" })
@EntityScan("com.mayikt.entity")
public class MayiKtApplication {
    public static void main(String[] args) {
        SpringApplication.run(MayiKtApplication.class,args);
//        System.out.println(SecurityConfig.passwordEncoder().encode("123"));
    }
}
