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
            sh "${env.WORKSPACE}/build.sh package"
          }
      }

      stage("发布代码"){
        steps{
           echo "publish code is ok "
        }
      }

    }
}