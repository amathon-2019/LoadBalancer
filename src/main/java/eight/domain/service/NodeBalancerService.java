package eight.domain.service;

import static eight.controller.BalancerController.MODE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eight.domain.balancer.LeastConnectionNodeBalancer;
import eight.domain.balancer.NodeBalancer;
import eight.domain.balancer.RoundRobinNodeBalancer;

@Component
public class NodeBalancerService implements NodeBalancer {

//	@Value("${loadBalancer.mode}")
//	String mode;

	@Autowired
	LeastConnectionNodeBalancer leastConnectionNodeBalancer;

	@Autowired
	RoundRobinNodeBalancer roundRobinNodeBalancer;

	@Override
	public String execute(int inBoundPort) {
		if (MODE.equals("roundRobin")) {
			return roundRobinNodeBalancer.execute(inBoundPort);
		}

		if (MODE.equals("leastConnection")) {
			return leastConnectionNodeBalancer.execute(inBoundPort);
		}

		throw new RuntimeException("no available mode founded");
	}
}
