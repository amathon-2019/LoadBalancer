package eight.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import eight.model.TargetGroup;
import eight.repository.TargetGroupRepository;

@Configuration
@EntityScan(basePackageClasses = { Jsr310JpaConverters.class, TargetGroup.class })
@EnableJpaRepositories(basePackageClasses = { TargetGroupRepository.class })
@EnableJpaAuditing
public class JpaConfig {

}
