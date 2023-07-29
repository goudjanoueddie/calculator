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
                                              changelog:true,
                                              poll:true
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
          stage("Docker build"){
                steps{
                //sh "docker build -t goudjanoueddie/calculator ."
                  //sh "docker run -ti --rm -v /var/run/docker.sock:/var/run/docker.sock"
                    sh "docker run --privileged --name dind -d docker:stable-dind"
                }
          }
     }
     /**workspace clean Up*/
    /* post{
        always{
            cleanWs()
        }
     }*/

     /*Add email notification to pipeline*/
     /*post{
        always{
            mail to:'goudjanoueddie@gmail.com',
            subject:"Completed Pipeline: ${currentBuild.fullDisplayName}",
            body:"Your Build completed, please check: ${env.BUILD_URL}"
        }
     }*/
} 
