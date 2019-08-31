package eight.controller;

import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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

		String destination;
		if (req.getSession().getAttribute("leastDestination") == null) {
			destination = nodeBalancerLauncher.execute(inBoundPort);
		} else {
			destination = req.getSession().getAttribute("leastDestination").toString();
		}

		String destinationForm = getDestinationForm(destination, reuqestUri);

		HttpHeaders headers = new HttpHeaders();

		String serverSessionKey = destinationForm + "key";

		if (req.getSession().getAttribute(serverSessionKey) != null) {
			headers.set(HttpHeaders.COOKIE, req.getSession().getAttribute(serverSessionKey).toString());
		}

		HttpEntity<String> entity = new HttpEntity<String>(headers);

		AtomicInteger con = connectionPool.getConnection(destination);
		con.getAndIncrement();
		ResponseEntity<String> res = new RestTemplate().exchange(destinationForm, HttpMethod.GET, entity, String.class);
		con.getAndDecrement();

		String body = res.getBody();

		if (req.getSession().getAttribute(serverSessionKey) == null) {
			String session = res.getHeaders().get("Set-Cookie").get(0).split(";")[0];
			req.getSession().setAttribute(serverSessionKey, session);
			req.getSession().setAttribute("leastDestination", destination);
		}

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
