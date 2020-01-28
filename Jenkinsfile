pipeline {
	 agent {
		label 'master'
	 }
	 triggers {
        pollSCM('H/5 * * * *')
    }
    options {
    disableConcurrentBuilds()
    buildDiscarder(logRotator(numToKeepStr: '30', daysToKeepStr: '90'))
    }
    stages {
        stage('Clean and checkout project') {
            steps{
                deleteDir()
                checkout(changelog: false, scm: scm)
            }
        }
        stage('Build') {
            steps{
                sh "mvn package"
            }
        }
        stage('Inspection') {
             steps{
                sh "mvn sonar:sonar \
                  -Dsonar.projectKey=fr.epsi.erp \
                  -Dsonar.host.url=http://35.204.133.106 \
                  -Dsonar.login=b4305e46e9938752ae37809a4da4dadc25ec1a2f"
             }
        }
    }
    post {
        always {
            step([$class: 'Mailer', recipients: [emailextrecipients([[$class:
            'CulpritsRecipientProvider'], [$class: 'RequesterRecipientProvider']])].join(' ')])
            archiveArtifacts 'target/*.jar'
            junit 'target/surefire-reports/*.xml'
        }
    }
}