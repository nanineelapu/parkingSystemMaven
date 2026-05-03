pipeline {
    agent any

    // Ensure you have configured 'Maven 3' and 'JDK 17' in Jenkins Global Tool Configuration.
    // You can remove the 'tools' block if they are already on the system PATH of the Jenkins agent.
    tools {
        maven 'Maven 3'
        jdk 'JDK 17'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Backend (Maven)') {
            steps {
                // We navigate into the backend folder where the pom.xml is located
                dir('backend') {
                    script {
                        // Support for both Windows (bat) and Linux/macOS (sh) Jenkins agents
                        if (isUnix()) {
                            sh 'mvn clean install'
                        } else {
                            bat 'mvn clean install'
                        }
                    }
                }
            }
        }

        stage('Build Frontend (Node/Vite)') {
            steps {
                // We navigate into the frontend folder where package.json is located
                dir('frontend') {
                    script {
                        if (isUnix()) {
                            sh 'npm install'
                            sh 'npm run build'
                        } else {
                            bat 'npm install'
                            bat 'npm run build'
                        }
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution complete.'
        }
        success {
            echo 'Build succeeded! Artifacts are ready.'
        }
        failure {
            echo 'Build failed. Check the logs.'
        }
    }
}
