package eight.controller;

import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import eight.domain.service.NodeBalancerService;
import eight.util.ConnectionPool;

@RestController
public class BalancerController extends AbstractContoller {
	public static String MODE = null;
	
	@Autowired
	NodeBalancerService nodeBalancerLauncher;

	@Autowired
	ConnectionPool connectionPool;

	@GetMapping("*")
	public String main(HttpServletRequest req) throws UnknownHostException {
		int inBoundPort = req.getLocalPort();
		String reuqestUri = req.getRequestURI();
		
		MODE = req.getParameter("mode");

		String destination = nodeBalancerLauncher.execute(inBoundPort);
		String destinationForm = getDestinationForm(destination, reuqestUri);

		AtomicInteger con = connectionPool.getConnection(destination);

		con.getAndIncrement();
		ResponseEntity<String> res = new RestTemplate().getForEntity(destinationForm, String.class);
		con.getAndDecrement();

		String body = res.getBody();
//		System.out.println(body);
		return body;
	}

	private String getDestinationForm(String host, String uri) {
		final String protocol = "http://";
		StringBuffer sb = new StringBuffer();
		sb.append(protocol);
		sb.append(host);
		sb.append(uri);
		return sb.toString();
	}
}
