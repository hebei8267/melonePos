

# memcached下载
docker pull memcached
# memcached启动
docker run -p 11211:11211 --name memcache memcached
# 51eq应用下载
docker login --username=hi31821161@aliyun.com registry.cn-hangzhou.aliyuncs.com --password=bjwFViCgBYG98m#$
docker pull registry.cn-hangzhou.aliyuncs.com/xgpos/51eq:latest
# 51eq应用启动
docker run -d -p 9090:8080  registry.cn-hangzhou.aliyuncs.com/xgpos/51eq:latest
# 51eq访问地址
http://47.95.249.10:9090/oa
http://hr.xgpos.com:9090/oa



# ==============================================================================================================

# 51eq应用打包
docker build -f Dockerfile -t registry.cn-hangzhou.aliyuncs.com/xgpos/51eq:latest --rm .

# 将镜像推送到Registry
docker login --username=hi31821161@aliyun.com registry.cn-hangzhou.aliyuncs.com --password=bjwFViCgBYG98m#$
docker push registry.cn-hangzhou.aliyuncs.com/xgpos/51eq:latest
