pipeline {
    agent any
    stages {
      stage("拉取代码"){
        steps{
           echo "get code is ok "
        }
      }
      stage("质量检查"){
        steps{
           echo "check code is ok "
        }
      }
      stage("构建代码"){
         steps{
            sh "chmod +x ${env.WORKSPACE}/build.sh && sudo ${env.WORKSPACE}/builder.sh build"
          }
      }

      stage("发布代码"){
        steps{
           echo "publish code is ok "
        }
      }

    }
}