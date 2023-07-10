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
          stage("Compilex") {
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
     }
     /**workspace clean Up*/
     /**post{
        always{
            cleanWs()
        }
     }*/

     /*Add email notification to pipeline*/
     post{
        always{
            mail to:'goudjanoueddie@gmail.com',
            subject:"Completed Pipeline: ${currentBuild.fullDisplayName}",
            body:"Your Build completed, please check: ${env.BUILD_URL}"
        }
     }
} 
