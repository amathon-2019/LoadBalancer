package eight.domain.balancer;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eight.domain.repository.TargetGroupRepository;
import eight.model.TargetGroup;
import eight.util.ConnectionPool;

@Component
class RoundRobinNodeBalancer implements NodeBalancer {

	@Autowired
	TargetGroupRepository targetGroupRepository;

	@Autowired
	ConnectionPool connectionPool;

	List<TargetGroup> targetGroupList;

	AtomicInteger count = new AtomicInteger(0);

	@Override
	public HttpServletResponse execute() {
		// TODO Auto-generated method stub
		targetGroupList = targetGroupRepository.findAll();

		int index = count.getAndIncrement() % targetGroupList.size();

		TargetGroup target = targetGroupList.get(index);

		String destination = target.getHost()+":"+target.getInboundPort();

		AtomicInteger con = connectionPool.getConnection(destination);
		con.getAndIncrement();

		System.out.println(con);

		return null;
	}
}
