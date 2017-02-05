#!/usr/bin/env bash
mvn install:install-file -DgroupId=com.microsoft.z3 -DartifactId=z3 -Dversion=SNAPSHOT -Dfile=com.microsoft.z3.jar -Dpackaging=jar -Dtype=jar