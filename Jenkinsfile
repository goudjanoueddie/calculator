#!/usr/bin/env groovy
pipeline {
     agent any 
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
     }
     /**workspace clean Up*/
     /**post{
        always{
            cleanWs()
        }
     }*/
} 
