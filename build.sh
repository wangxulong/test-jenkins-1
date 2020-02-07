#!/bin/bash

# 设置环境变量
# 参数
action=$1
# 打包
dev_package(){
   echo "开始打包"
    docker run --rm -w /app -v $HOME/.m2:/root/.m2 -v `pwd`:/app \
        maven:3.6.1-jdk-11-slim \
        mvn clean package
     echo "打包结束"
   mvn clean package
}

if [[ ${action} == "package" ]]; then
    dev_package
fi
