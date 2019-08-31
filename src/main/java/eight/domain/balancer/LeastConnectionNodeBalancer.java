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
class LeastConnectionNodeBalancer implements NodeBalancer {

	@Autowired
	TargetGroupRepository targetGroupRepository;

	@Autowired
	ConnectionPool connectionPool;

	List<TargetGroup> targetGroupList;

	@Override
	public HttpServletResponse execute() {
		targetGroupList = targetGroupRepository.findAll();

		// targetGroupList = targetGroupRepository.findAllByIdOrderByConnection(80);

		TargetGroup target = targetGroupList.get(0);

		String destination = target.getHost() + ":" + target.getInboundPort();

		AtomicInteger con = connectionPool.getConnection(destination);
		con.getAndIncrement();

		return null;
	}

}
