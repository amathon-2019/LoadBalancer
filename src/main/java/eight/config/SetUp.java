package eight.config;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import eight.domain.repository.TargetGroupRepository;
import eight.model.TargetGroup;
import eight.util.ConnectionPool;

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
