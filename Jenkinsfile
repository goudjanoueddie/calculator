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
          stage("Docker build"){
                steps{
                sh "docker build -t goudjanoueddie/calculator ."
                }
          }
        stage("Docker push"){
            steps{
                sh "docker push goudjanoueddie/calculator"
            }
        }

    }
     /**workspace clean Up*/
    post{
        always{
            cleanWs()
        }
     }

}
