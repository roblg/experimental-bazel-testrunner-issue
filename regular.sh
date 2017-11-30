#!/bin/bash
set -x
bazel test //src/java:test
cat bazel-testlogs/src/java/test/test.log
