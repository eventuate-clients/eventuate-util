#! /bin/bash -e


BRANCH=$(git rev-parse --abbrev-ref HEAD)

if [  $BRANCH == "master" ] ; then
  BUILD_SNAPSHOT=$(sed -e '/^version=/!d' -e 's/^version=\(.*\)-SNAPSHOT$/\1.BUILD-SNAPSHOT/' < gradle.properties)
  echo master: publishing $BUILD_SNAPSHOT
  ./gradlew -P version=$BUILD_SNAPSHOT -P deployUrl=${S3_REPO_DEPLOY_URL} uploadArchives
  exit 0
fi

if ! [[  $BRANCH =~ ^[0-9]+ ]] ; then
  echo Not release $BRANCH
  exit 0
fi

VERSION=$BRANCH

$PREFIX ./gradlew -P version=${VERSION} \
  -P deployUrl=https://dl.bintray.com/eventuateio-oss/eventuate-maven-release \
  testClasses bintrayUpload
