pipeline {
    agent none
   stages {     
    stage('Maven Install') {
      agent {         
       docker {          
         image 'maven 3.8.2'         
     }       
  }       
  steps {
       sh 'mvn clean install'
       }
     }
   }
 }