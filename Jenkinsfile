 pipeline {
 agent any
 stages{ 
    
    
    stage('clean') {
       
       steps{
       
       withMaven(maven: 'maven 3.8.2') {
		   'clean install'
		}
       
           echo "Cleaning the project"
           sh 'mvn clean'
       }
    }
    
    stage('Test') {
        
        steps {
            echo "Doing the tests"
            sh 'mvn test'
         
            }
            
        
        }
   
    stage('MVN PACKAGE') {
       steps{
           echo "packing the project";
           sh 'mvn package';
       }
       post{
           success {
            archiveArtifacts 'target/*.jar';
           }
       }
    }
    

     
   
  
    }
}