package eight.domain.balancer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NodeBalancerLauncher implements NodeBalancer {
	@Autowired
	LeastConnectionNodeBalancer leastConnectionNodeBalancer;

	@Autowired
	RoundRobinNodeBalancer RoundRobinNodeBalancer;

	@Override
	public void execute() {
		// config mode read

		// 분기처리

	}
}
