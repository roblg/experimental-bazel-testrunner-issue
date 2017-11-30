filegroup(
    name="srcs",
    srcs=glob([
        "**/*.java"
    ]),
    visibility=["//visibility:public"],
)

java_test(
    name="test",
    srcs=[":srcs"],
    test_class="com.example.ParameterizedTest",
    deps=[
        "@junit//jar",
        "@hamcrest_core//jar",
    ],
    visibility=["//visibility:public"],
)