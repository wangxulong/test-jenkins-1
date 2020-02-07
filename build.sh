#!/bin/bash

# 参数
action=$1
# 打包
dev_package(){
   mvn clean package
}

if [[ ${action} == "package" ]]; then
    dev_package
fi