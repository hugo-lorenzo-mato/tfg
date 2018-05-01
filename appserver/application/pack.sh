#!/usr/bin/env bash

set -e

ARTIFACT=$1
VERSION=$2

# Pack
PACKAGE="${ARTIFACT}-${VERSION}"
cd build/libs
mkdir -p ${PACKAGE}
cp *.war ${PACKAGE}
cp -r ../db/migrations ${PACKAGE}
zip -r ${ARTIFACT}.zip ${PACKAGE}

echo "Packed: ${ARTIFACT}.zip"
