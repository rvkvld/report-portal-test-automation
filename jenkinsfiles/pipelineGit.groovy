pipeline {
    agent {label 'any'}
    stages{
        stage('Pre-Build Cleanup') {
            steps {
                deleteDir() //This will remove the workspace before each build
            }
        }
        stage ('Checkout Repo'){
            steps {
                script {
                    deleteDir()
                    checkout([$class: 'GitSCM', branches: [[name: '$branch']]])
                }
            }
        }
        stage ('e2e-test') {
            agent {
                docker {
                    image 'mcr.microsoft.com/playwright/java:v1.40.0-jammy'
                    reuseNode true
                }
            }
            steps {
                sh 'mvn -B install -D skipTests --no-transfer-progress'
                sh 'mvn test'
            }
        }
    }
}