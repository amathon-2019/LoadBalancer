package eight.controller;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import eight.service.TagetGroupService;

@RestController
@CrossOrigin
public class BalancerController {
	@Autowired
	TagetGroupService tagetGroupService;

	@GetMapping("/")
	public String main(HttpServletRequest req) throws UnknownHostException {
		System.out.println(req.getHeader("Host")); // 요청한 곳
		tagetGroupService.sample();

		return "Simple Load Balancer";
	}
}
