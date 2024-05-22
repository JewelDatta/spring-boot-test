pipeline {
    agent {
        node {
            label 'docker-custom-agent'
        }
    }
    stages {
        stage('Checkout') {
            steps {
                sh 'echo checkout stage'
                git branch: 'main', url: 'https://github.com/JewelDatta/spring-boot-test'
            }
        }
        
    
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests' // Builds the project and skips the tests
            }
        }
    
        stage('Test') {
            steps {
                sh 'mvn test' // Runs the tests
            }
        }
    }
}
