#!/bin/bash

# 设置环境变量
echo $JENKINS_HOME
echo `pwd`
export PATH=$JENKINS_HOME/maven/bin:$PATH
# 参数
action=$1
# 打包
dev_package(){
   mvn clean package
}

if [[ ${action} == "package" ]]; then
    dev_package
fi

dev_package