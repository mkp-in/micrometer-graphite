# micrometer-graphite

This is a sample bare skeleton project to demonstrate SpringBoot 2 pushing Metrics to graphite.

## Files of interest:
* BookConf: This is where we instantiate the GraphiteMetricsRegistry.
* MyGraphiteHierarchicalNameMapper: Define the hierarchy rules.
* application.yml: Enable Graphite and set its parameters.
* Docker/runLocalGraphiteOnDocker.sh: A simple bash script to run graphite on Docker to test your code.

_*Spring Boot auto-configures a composite MeterRegistry and adds a registry to the composite for each of the supported implementations that it finds on the classpath. Having a dependency on micrometer-registry-{system} in your runtime classpath is enough for Spring Boot to configure the registry.*_

