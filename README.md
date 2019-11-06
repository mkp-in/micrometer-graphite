# micrometer-graphite

This is a sample bare skeleton project to demonstrate SpringBoot 2 pushing Metrics to graphite.

## Files of interest:
BookConf: This is where we instantiate the GraphiteMetricsRegistry.
MyGraphiteHierarchicalNameMapper: Define the hierarchy rules.
application.yml: Enable Graphite and its set its parameters.
Docker/runLocalGraphiteOnDocker.sh: A simple bash script to run graphite on Docker to test your code.