package in.mkp.micrometergraphite.conf;

import in.mkp.micrometergraphite.MyGraphiteHierarchicalNameMapper;
import io.micrometer.core.instrument.Clock;
import io.micrometer.graphite.GraphiteConfig;
import io.micrometer.graphite.GraphiteMeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookConf {

    @Bean
    public GraphiteMeterRegistry graphiteMeterRegistry(final GraphiteConfig config, final Clock clock) {
        final MyGraphiteHierarchicalNameMapper graphiteHierarchicalNameMapper = new MyGraphiteHierarchicalNameMapper("in.mkp");
        return new GraphiteMeterRegistry(config, clock, graphiteHierarchicalNameMapper);
    }
}
