package at.itproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class ApiServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiServiceApplication.class, args);
    }

    /*
    @Bean({"slackHeader"})
    public HttpHeaders slackHeader() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth("xoxp-877272772119-877620818278-877283173479-f7d9d6b06cc79954dc9eb57143d80bec");
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }
    */


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
