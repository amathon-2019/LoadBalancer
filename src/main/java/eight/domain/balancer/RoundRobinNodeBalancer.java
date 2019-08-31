package eight.domain.balancer;

import eight.model.TargetGroup;
import eight.repository.TargetGroupRepository;
import eight.util.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
