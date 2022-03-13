#!/usr/bin/env bash
./gradlew nativeBinaries
mv ./build/bin/native/releaseExecutable/DevSetter.kexe /usr/local/bin/devset