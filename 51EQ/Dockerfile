#FROM inovatrend/tomcat7-java7

# 使用自己SSL配置的tomcat镜像
FROM registry.cn-hangzhou.aliyuncs.com/xgpos/51eq:tomcat20220105

MAINTAINER "hebei <hebei@rawstonedu.com>"


COPY ./src/main/webapp/ /opt/tomcat/webapps/oa/


EXPOSE 8080 443
