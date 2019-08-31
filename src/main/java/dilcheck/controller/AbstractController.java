package dilcheck.controller;

import java.net.UnknownHostException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AbstractController {
  @GetMapping("/")
  public String main(HttpServletRequest req) throws UnknownHostException {
    System.out.println(req.getHeader("Host")); // 요청한 곳
    return "Simple Load Balancer";
  }
}
