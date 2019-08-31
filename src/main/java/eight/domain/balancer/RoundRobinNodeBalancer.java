package eight.domain.balancer;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eight.domain.repository.TargetGroupRepository;
import eight.model.TargetGroup;
import eight.util.ConnectionPool;

@Component
public class RoundRobinNodeBalancer implements NodeBalancer {

	@Autowired
	TargetGroupRepository targetGroupRepository;

	@Autowired
	ConnectionPool connectionPool;

	List<TargetGroup> targetGroupList;

	AtomicInteger count = new AtomicInteger(0);

	@Override
	public String execute(int inBoundPort) {
		// TODO Auto-generated method stub
		targetGroupList = targetGroupRepository.findAll();

		int index = count.getAndIncrement() % targetGroupList.size();

		TargetGroup target = targetGroupList.get(index);

		String destination = target.getHost() + ":" + target.getOutboundPort();

		return destination;
	}
}
