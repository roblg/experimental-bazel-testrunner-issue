
# Classpath Issue w/ bazel `--experimental_testrunner`

This repo illustrates a possible issue w/ the bazel experimental test runner.

`ParameterizedTest.java` uses the `Parameterized` runner, which creates a new
instance of the test class with different parameters for each result returned
from a method annotated with `@Parameters`. The `Parameterized` runner works with
both JUnit 4.11 and 4.12, but appears to have gotten more flexible in 4.12 --
allowed `@Parameters` method APIs were extended to allow Object[][] in addition to
Iterable<Object[]>. (sources
[https://github.com/junit-team/junit4/blob/r4.11/src/main/java/org/junit/runners/Parameterized.java#L291-L298](4.11), 
[https://github.com/junit-team/junit4/blob/r4.12/src/main/java/org/junit/runners/Parameterized.java#L279-L288](4.12)).

The experimental test runner _appears_, based on code and comments, to be trying to
isolate the tests from the bazel runner by creating a separate classloader, but the
it seems like classloader being created still depends on the system classloader as its
parent, and so JUnit from the system classloader is resolved from the tests as well.

Examples:

Run the regular test runner:
```bash
./regular.sh

```

You'll see in the output that the system classloader and the classloaders used by
all the classes in the test's list are the same.

Run the experimental test runner:
```bash
./experimental.sh
```

and you'll see in the output that the JUnit classes resolved from `ParameterizedTest`
were loaded by the system classloader, but the test class itself was resolved from
the `URLClassLoader` set up in the experimental runner.