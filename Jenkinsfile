pipeline {
    agent {
        docker {
          image 'abhishekf5/maven-abhishek-docker-agent:v1'
          args '--user root -v /var/run/docker.sock:/var/run/docker.sock' // mount Docker socket to access the host's Docker daemon
        }
    }
    stages {
        stage('Checkout') {
            steps {
                sh 'echo checkout stage'
                git branch: 'main', url: 'https://github.com/JewelDatta/spring-boot-test    '
            }
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