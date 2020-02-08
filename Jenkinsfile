pipeline {
    agent any
    stages {
      stage("编译"){
         steps{
            sh "chmod 777 ${env.WORKSPACE}/build.sh &&  ${env.WORKSPACE}/build.sh package"
          }
      }
       stage("质量检查"){
              steps{
                  script {
                      scannerHome = tool 'sonarScanner'
                 }
                  withSonarQubeEnv("SonarServer") {
                      sh "${scannerHome}/bin/sonar-scanner"
                  }
               }




            }

      stage("代码覆盖统计"){
         steps {
            jacoco()
        }
      }


    }
}