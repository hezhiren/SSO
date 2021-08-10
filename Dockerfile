FROM openjdk:8-jdk-alpine
VOLUME ["/logs"]

RUN apk --no-cache add tzdata ttf-dejavu && \
    ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
    echo "Asia/Shanghai" > /etc/timezone

ARG JAR_FILE

COPY config /home/
#COPY templates /home/

COPY ${JAR_FILE} sso.jar

ENV JAVA_OPTS=""
# 添家JVM参数
ENV JAVA_OPTS="-server -Xmx1024M -Xms1024M -Xmn750M -XX:MetaspaceSize=256M \
-XX:MaxMetaspaceSize=512M -XX:GCTimeRatio=19 -XX:+ClassUnloading -XX:+UseConcMarkSweepGC -Xloggc:logs/gc.log"

EXPOSE 9001

ENTRYPOINT java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /sso.jar
