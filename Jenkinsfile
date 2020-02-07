pipeline {
    agent any
    stages {

      stage("构建代码"){
         steps{
            sh "chmod 777 ${env.WORKSPACE}/build.sh &&  ${env.WORKSPACE}/build.sh package"
          }
      }


    }
}