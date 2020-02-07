pipeline {
    agent any
    stages {

      stage("质量检查"){

          def scannerHome = tool 'sonarScanner';
          echo scannerHome
           withSonarQubeEnv(credentialsId: 'test-pipeline') {
              sh "${scannerHome}/bin/sonar-scanner"
            }


      }
      stage("打包"){
         steps{
            sh "chmod 777 ${env.WORKSPACE}/build.sh &&  ${env.WORKSPACE}/build.sh package"
          }
      }

      stage("代码覆盖统计"){
         steps {
            jacoco()
        }
      }


    }
}