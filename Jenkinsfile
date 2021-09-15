 pipeline {
 agent any
 stages{ 
    
    
    stage('clean') {
       
       steps{
           echo "Cleaning the project"
           bat 'mvn clean'
       }
    }
    
    stage('Test') {
        
        steps {
            echo "Doing the tests"
            bat 'mvn test'
         
            }
            
        
        }
   
    stage('MVN PACKAGE') {
       steps{
           echo "packing the project";
           bat 'mvn package';
       }
       post{
           success {
            archiveArtifacts 'target/*.jar';
           }
       }
    }
    

     
   
  
    }
}