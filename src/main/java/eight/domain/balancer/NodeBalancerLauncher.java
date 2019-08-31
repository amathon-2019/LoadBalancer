package eight.domain.balancer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
public class NodeBalancerLauncher implements NodeBalancer {

	@Autowired
	LeastConnectionNodeBalancer leastConnectionNodeBalancer;

	@Autowired
	RoundRobinNodeBalancer roundRobinNodeBalancer;

	@Value("${loadBalancer.Node}")
	String Node;

	@Override
	public HttpServletResponse execute() {
		// config mode read

		// 분기처리
		if(Node.equals("roundRobin"))
		{
			roundRobinNodeBalancer.execute();
		}
		else if(Node.equals("leastConnection"))
		{
			leastConnectionNodeBalancer.execute();
		}
		else
		{
			throw new RuntimeException("no available mode founded");
		}

		System.out.println(Node);

		return null;
	}
}
