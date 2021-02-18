FROM inovatrend/tomcat7-java7

MAINTAINER "hebei <hebei@rawstonedu.com>"



COPY ./src/main/webapp/ /opt/tomcat/webapps/oa/


ENV JAVA_OPTS="-Dfile.encoding=UTF8 -Duser.timezone=GMT+08"
ENV TZ=Asia/Shanghai
EXPOSE 8080
