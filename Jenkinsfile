pipeline {
    agent any
    triggers {
        githubPush()
    }
    stages {
        stage('Build') {
            steps {
                setBuildStatus('Build Pending...', 'PENDING');    
                echo 'Building...'
            }
        }
        stage('Unit Test') {
            steps {
                echo 'Unit Testing...'
            }
        }
        stage('Create and Upload Docker Image') {
            steps {
                echo 'Create and Upload Docker Image...'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying...' 
            }
        }
        stage('Functional Test') {
            steps {
                echo 'Functional Testing...'
            }
        }
    }
    post {
        success {
            setBuildStatus("Build succeeded", "SUCCESS");
        }
        failure {
            setBuildStatus("Build failed", "FAILURE");
        }
    }
}
void setBuildStatus(String message, String state) {
    step([
        $class: "GitHubCommitStatusSetter",
        reposSource: [$class: "ManuallyEnteredRepositorySource", url: "https://github.com/jay-ar-bautista-novare/gradle-rest-api-app"],
        contextSource: [$class: "ManuallyEnteredCommitContextSource", context: "ci/jenkins/build-status"],
        errorHandlers: [[$class: "ChangingBuildStatusErrorHandler", result: "UNSTABLE"]],
        statusResultSource: [ $class: "ConditionalStatusResultSource", results: [[$class: "AnyBuildResult", message: message, state: state]] ]
    ]);
}
