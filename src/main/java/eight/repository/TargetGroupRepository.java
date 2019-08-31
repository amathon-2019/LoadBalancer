package eight.repository;

import com.sun.tools.javac.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eight.model.TargetGroup;

@Repository
public interface TargetGroupRepository extends JpaRepository<TargetGroup, Integer> {
    //List<TargetGroup> findAllByIdOrderByConnection(int inboundPort);
}
