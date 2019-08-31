package eight.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eight.model.TargetGroup;

@Repository
public interface TargetGroupRepository extends JpaRepository<TargetGroup, String> {
	List<TargetGroup> findAllByInBoundPort(Integer inBoundPort);

	List<TargetGroup> findAllByInBoundPortOrderByConnection(Integer inBoundPort);
}
