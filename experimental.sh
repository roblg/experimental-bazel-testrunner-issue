#!/bin/bash
set -x
bazel test --experimental_testrunner //src/java:test
cat bazel-testlogs/src/java/test/test.log
