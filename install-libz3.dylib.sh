#!/usr/bin/env bash
mvn install:install-file -DgroupId=com.microsoft.z3 -DartifactId=libz3 -Dversion=SNAPSHOT -Dfile=libz3.dylib -Dpackaging=dylib -Dtype=dylib