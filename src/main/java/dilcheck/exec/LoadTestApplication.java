package dilcheck.exec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("dilcheck.*")
public class LoadTestApplication {

  public static void main(String[] args) {
    SpringApplication.run(LoadTestApplication.class, args);
  }
}
