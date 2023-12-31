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
          /*stage("Code Coverage"){
                steps{
                    sh "./gradlew jacocoTestReport"
                    sh "./gradlew jacocoTestCoverageVerification"
                }
          }*/
          stage("Package"){
                steps{
                    sh "./gradlew build"
                }
          }
          stage("Docker build Step"){
                steps{
                sh "docker build -t goudjanoueddie/calculator ."
                }
          }
        stage("Docker push"){
            steps{
                withDockerRegistry([ credentialsId: "docker-hub-credentials", url: "" ]){
                    sh "docker push goudjanoueddie/calculator"
                }

            }
        }
        stage("Deploy to staging"){
            steps{
                sh "docker run -d --rm -p 8765:8080 --name calculator goudjanoueddie/calculator"
            }
        }
        stage("Acceptance test without kubernetes"){
            steps{
                //wait sixty milliseconds
                sleep 60
                sh "chmod +x acceptance_test.sh && ./acceptance_test.sh"
            }
        }

    }
     /**workspace clean Up*/
    post{
        always{
            sh "docker stop calculator"
            cleanWs()
        }
     }

}
