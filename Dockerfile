FROM java:8
MAINTAINER wangxulong
VOLUME /tmp
ADD ./target/test-jenkins-0.0.1-SNAPSHOT.jar /app/nas_cloud.jar

