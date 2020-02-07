pipeline {
    agent any
    stages {

      stage("构建代码"){
         steps{
            sh "${env.WORKSPACE}/build.sh package"
          }
      }


    }
}