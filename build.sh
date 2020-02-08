#!/bin/bash

# 设置环境变量
action=$1
# 打包
package(){
   echo "开始打包"
   # 使用镜像进行打包
   docker run --rm -w /app -v $HOME/.m2:/root/.m2 -v `pwd`:/app \
        maven:3.6.1-jdk-11-slim \
        mvn clean package
   echo "打包结束"

}
#发布
deploy(){
    echo "开始发布"
    docker-compose down
    docker-compose up
    echo "发布结束"
}

if [[ ${action} == "package" ]]; then
    package
fi
if [[ ${action} == "deploy" ]]; then
    deploy
fi