package eight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eight.model.TargetGroup;
import eight.repository.TargetGroupRepository;

@Service
public class TagetGroupService extends AbstractService {
	@Autowired
	TargetGroupRepository targetGroupRepository;

	public void sample() {
		logger.info("test call");

		TargetGroup targetGroup = new TargetGroup();
		targetGroup.setHost("test");
		targetGroup.setInboundPort(80);

		targetGroupRepository.save(targetGroup);

		targetGroupRepository.findAll().forEach(t -> {
			System.out.println(t.getInboundPort());
		});
	}
}
