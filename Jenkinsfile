node {

    deleteDir()

    try {
        stage ('Clone') {
        	checkout scm
        }
        stage ('Build') {
        	sh "echo 'shell scripts to build project...'"
        	sh "./mvnw clean install"
        }
        stage ('Tests') {

	        sh "echo 'running tests...'"
	        sh "./mvnw test"

        }

        stage('SSH to VM & Deploy'){
            echo "Connecting to VM for deploying service"
            withCredentials([sshUserPrivateKey(credentialsId: '998bfa1e-8cc9-4837-a53c-34a41f2cbe9a', keyFileVariable: '', passphraseVariable: '', usernameVariable: '')]) {
                // some block
                sh "echo 'shell scripts to deploy to server...'"
                sh "java -jar target/demonews-0.0.1-SNAPSHOT.jar"
            }

        }

    } catch (err) {
        currentBuild.result = 'FAILED'
        throw err
    }
}

