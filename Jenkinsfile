pipeline {
    agent any
    stages {

      stage("构建代码"){
         steps{
            sh "sudo ${env.WORKSPACE}/build.sh package"
          }
      }


    }
}