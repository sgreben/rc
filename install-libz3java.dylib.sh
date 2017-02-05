#!/usr/bin/env bash
mvn install:install-file -DgroupId=com.microsoft.z3 -DartifactId=libz3java -Dversion=SNAPSHOT -Dfile=libz3java.dylib -Dpackaging=dylib -Dtype=dylib
