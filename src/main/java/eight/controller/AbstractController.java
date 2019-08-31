package eight.controller;

import java.net.UnknownHostException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import eight.model.TargetGroup;
import eight.repository.TargetGroupRepository;

@RestController
@CrossOrigin
public class AbstractController {

	@Autowired
	TargetGroupRepository targetGroupRepository;

	@GetMapping("/")
	public String main(HttpServletRequest req) throws UnknownHostException {
		System.out.println(req.getHeader("Host")); // 요청한 곳

		TargetGroup targetGroup = new TargetGroup();
		targetGroup.setHost("test");
		targetGroup.setServerName("test");

		targetGroupRepository.save(targetGroup);

		targetGroupRepository.findAll().forEach(t -> {
			System.out.println(t);
		});

		return "Simple Load Balancer";
	}
}
