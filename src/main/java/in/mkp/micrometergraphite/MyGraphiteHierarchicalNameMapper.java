package in.mkp.micrometergraphite;

import java.util.stream.Collectors;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.config.NamingConvention;
import io.micrometer.core.instrument.util.HierarchicalNameMapper;
import io.micrometer.core.instrument.util.StringUtils;

public class MyGraphiteHierarchicalNameMapper implements HierarchicalNameMapper {

    private final String prefix;

    public MyGraphiteHierarchicalNameMapper(final String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toHierarchicalName(final Meter.Id id, final NamingConvention convention) {
        final String name = id.getName();
        String tags = "";

        if (id.getTags().iterator().hasNext()) {
            tags = "." + id.getConventionTags(convention).stream()
                    .map(t -> t.getKey() + "." + t.getValue())
                    .map(nameSegment -> nameSegment.replace(" ", "_"))
                    .collect(Collectors.joining("."));
        }

        if (name.contains("{") || name.contains("}")) {
            throw new IllegalArgumentException("Some placeholders in the metric name do not have a matching tag! " +
                    "Metric name: " + id.getName() + ", after resolving with tags provided: " + name);
        }

        return StringUtils.isNotEmpty(tags) ? this.prefix + "." + name + tags : this.prefix + "." + name;
    }
}
