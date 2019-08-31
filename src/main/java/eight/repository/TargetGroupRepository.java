package eight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eight.model.TargetGroup;

@Repository
public interface TargetGroupRepository extends JpaRepository<TargetGroup, String> {

}
