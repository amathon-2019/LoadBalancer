package eight.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import eight.domain.balancer.LeastConnectionNodeBalancer;
import eight.domain.balancer.NodeBalancer;
import eight.domain.balancer.RoundRobinNodeBalancer;

@Component
public class NodeBalancerService implements NodeBalancer {

	@Value("${loadBalancer.mode}")
	String mode;

	@Autowired
	LeastConnectionNodeBalancer leastConnectionNodeBalancer;

	@Autowired
	RoundRobinNodeBalancer roundRobinNodeBalancer;

	@Override
	public String execute(int inBoundPort) {
		if (mode.equals("roundRobin")) {
			return roundRobinNodeBalancer.execute(inBoundPort);
		}

		if (mode.equals("leastConnection")) {
			return leastConnectionNodeBalancer.execute(inBoundPort);
		}

		throw new RuntimeException("no available mode founded");
	}
}
