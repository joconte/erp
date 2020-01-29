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
                      -Dsonar.host.url=http://35.238.91.38 \
                      -Dsonar.login=28b856747c2ece1246339d399a4b360a0e04d23d"
             }
        }
    }
    post {
        always {
            step([$class: 'Mailer', recipients: [emailextrecipients([[$class:
            'CulpritsRecipientProvider'], [$class: 'RequesterRecipientProvider']])].join(' ')])
            discordSend description: "Jenkins Pipeline Build", footer: "Footer Text", link: env.BUILD_URL, result: currentBuild.currentResult, title: JOB_NAME, webhookURL: "https://discordapp.com/api/webhooks/671807536835461134/yuORHRLOU_JzhY_UiHiQOosvf1hR3ZnRkR-bmooUWP9tLnFatu98ntlzcRy5184lsMzO"
            archiveArtifacts 'target/*.jar'
            junit 'target/surefire-reports/*.xml'
        }
    }
}