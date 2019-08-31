package eight.config;

import java.util.List;
import eight.model.TargetGroup;
import eight.repository.TargetGroupRepository;
import eight.util.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class SetUp
{
    @Autowired
    ConnectionPool pool;

    @Autowired
    TargetGroupRepository targetGroupRepository;

    @PostConstruct
    public void setUp()
    {
        System.out.println(targetGroupRepository.findAll().size());
        for(TargetGroup targetGroup : targetGroupRepository.findAll())
        {
            pool.setConnection(targetGroup.getHost()+":"+targetGroup.getInboundPort(), new AtomicInteger(targetGroup.getConnection()));
        }
    }
}
