package eight.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eight.domain.repository.TargetGroupRepository;
import eight.model.TargetGroup;

@Service
public class TagetGroupService extends AbstractService {
	@Autowired
	TargetGroupRepository targetGroupRepository;

	public List<TargetGroup> findAllByInBoundPortOrderByConnection(int inBoundPort) {
		return targetGroupRepository.findAllByInBoundPortOrderByConnection(inBoundPort);

	}

}
