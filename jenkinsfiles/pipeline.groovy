pipeline {
    agent {label ''}
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
                    checkout([$class: 'GitSCM', branches: [[name: '$branch']], userRemoteConfigs: [[credentialsId: "sdf", url: "https://github.com/rvkvld/report-portal-test-automation"]]])
                }
            }
        }
        stage ('e2e-test') {
            agent {
                docker {
                    image 'mcr.microsoft.com/playwright/java:v1.30.0-focal'
                    reuseNode true
                }
            }
            steps{
                script {
                    sh 'mvn -B install -DskipTests --no-transfer-progress'
                    sh 'mvn clean test -Dtest.gent=browserstack allure:report'
                }
            }
        }
    }
    post {
        always {
            script {
                publishHTML (target: [
                        allwaysMissing          : false,
                        alwaysLinkToLastBuild   : false,
                        keepAll                 : true,
                        reportDir               : 'target/site/allure-maven-plugin',
                        reportFiles             : 'index.html',
                        reportName              : 'Playwright allure Report'
                ])
            }
        }
    }
}