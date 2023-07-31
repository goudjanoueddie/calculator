#!/usr/bin/env groovy
pipeline {
    agent any
    /*triggers{
        pollSCM('* * * * *')
     }*/
    stages {
        stage("Checkout") {
            steps {
                dir("neptune") {
                    git(
                            url: 'https://github.com/goudjanoueddie/calculator.git',
                            branch: 'main',
                            changelog: true,
                            poll: true
                    )
                }

            }
        }
        stage("Compile") {
            steps {
                sh "./gradlew compileJava"
            }
          }
          stage("Unit test") {
               steps { 
                    sh "./gradlew test" 
               } 
          }
          stage("Code Coverage"){
                steps{
                    sh "./gradlew jacocoTestReport"
                    sh "./gradlew jacocoTestCoverageVerification"
                }
          }
          stage("Package"){
                steps{
                    sh "./gradlew build"
                }
          }
          /*stage("Docker build"){
                steps{
                //sh "docker build -t goudjanoueddie/calculator ."
                  //sh "docker run -ti --rm -v /var/run/docker.sock:/var/run/docker.sock"
                    sh "docker run --privileged --name dind -d docker:stable-dind"
                    sh "docker exec -it dind /bin/ash"
                }
          }*/
         stage("Docker build"){
             agent {
                 docker {
                     image 'gradle:8.2.0-jdk17-alpine'
                     // Run the container on the node specified at the
                     // top-level of the Pipeline, in the same workspace,
                     // rather than on a new node entirely:
                     reuseNode true
                 }
             }
             steps {
                 sh 'gradle --version'
             }
     }
     /**workspace clean Up*/
    post{
        always{
            cleanWs()
        }
     }


}
}
