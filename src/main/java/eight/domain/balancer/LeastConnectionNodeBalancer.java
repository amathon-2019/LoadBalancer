package eight.domain.balancer;

import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eight.domain.repository.TargetGroupRepository;
import eight.model.TargetGroup;
import eight.util.ConnectionPool;

@Component
public class LeastConnectionNodeBalancer implements NodeBalancer {

	@Autowired
	TargetGroupRepository targetGroupRepository;

	List<TargetGroup> targetGroupList;

	@Autowired
	ConnectionPool connectionPool;

	@Override
	public String execute(int inBoundPort) {
		Stream<Entry<String, AtomicInteger>> entry = connectionPool.getEntry().stream();
		Stream<Entry<String, AtomicInteger>> sortedEntry = entry
				.sorted((e1, e2) -> e1.getValue().get() - e2.getValue().get());

		String destination = sortedEntry.findFirst().get().getKey();
		return destination;
	}

}
