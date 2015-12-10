package zones;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@EnableAutoConfiguration
@ComponentScan
public class ZonesApplication {

    public static void main(String[] args) {
        SpringApplication.run(zones.ZonesApplication.class, args);
    }
}
