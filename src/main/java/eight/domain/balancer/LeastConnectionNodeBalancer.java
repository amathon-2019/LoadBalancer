package eight.domain.balancer;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import eight.model.TargetGroup;
import eight.repository.TargetGroupRepository;
import eight.util.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

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

		//targetGroupList = targetGroupRepository.findAllByIdOrderByConnection(80);

		TargetGroup target = targetGroupList.get(0);

		String destination = target.getHost()+":"+target.getInboundPort();

		AtomicInteger con = connectionPool.getConnection(destination);
		con.getAndIncrement();


		return null;
	}

}
