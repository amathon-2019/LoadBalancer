package eight.controller;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import eight.domain.service.TagetGroupService;

@RestController
@CrossOrigin
public class BalancerController {
	@Autowired
	TagetGroupService tagetGroupService;
	
	
	int count = 0;

	@GetMapping("*")
	public String main(HttpServletRequest req) throws UnknownHostException {
		String target = req.getHeader("Host");
		int inBoundPort = req.getLocalPort();

		String reuqestUrI = req.getRequestURI();

		String[] ipList = new String[] { "13.209.26.89:8081", "54.180.150.98:8081" };

		// 54.180.150.98:8081 조립
		String destinationHost = ipList[count % ipList.length];
		String destination = getHttpForm(destinationHost, reuqestUrI);
		System.out.println(count % ipList.length);
		System.out.println(destination);

		ResponseEntity<String> res = new RestTemplate().getForEntity(destination, String.class);

		count++;
		return res.getBody();
	}

	private String getHttpForm(String host, String uri) {
		final String protocol = "http://";

		StringBuffer sb = new StringBuffer();
		sb.append(protocol);
		sb.append(host);
		sb.append(uri);
		return sb.toString();
	}

}
