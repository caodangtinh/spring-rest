def label = "worker-${UUID.randomUUID().toString()}"

podTemplate(label: label, containers: [
  containerTemplate(name: 'docker', image: 'docker', command: 'cat', ttyEnabled: true),
  containerTemplate(name: 'helm', image: 'core.harbor.domain/library/helm-kubectl:3.2.3', command: 'cat', ttyEnabled: true),
  containerTemplate(name: 'maven', image: 'maven:3.6.3-adoptopenjdk-8', command: 'cat', ttyEnabled: true),
  containerTemplate(name: 'jnlp', image: 'jenkinsci/jnlp-slave', args: '${computer.jnlpmac} ${computer.name}', envVars: [ 
      envVar(key: 'JENKINS_URL', value: 'http://jenkins.jenkins.svc.cluster.local:8080')
    ]
    )
],
volumes: [
  persistentVolumeClaim(mountPath: '/root/.m2/repository', claimName: 'mavenrepo', readOnly: false),
  hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock')
]) {
  node(label) {
    def myRepo = checkout scm
    def registry = "core.harbor.domain"
    def registry_ip ="192.168.99.102"
    def shortGitCommit = sh(script: "printf \$(git rev-parse --short HEAD)",returnStdout: true)
    def image = "backbase/spring-rest"
    def version = "0.0.${BUILD_NUMBER}"
    def helm_repo_url = "https://core.harbor.domain/chartrepo/backbase"
    def helm_repo = "harbor"
    def app = "spring-rest"
    container('maven') {
        stage('check java') {
            sh "java -version"
        }

        stage('clean') {
            sh "mvn clean"
        }
        
        stage('Testing') {
             sh "mvn test"
        }

        stage('Packaging') {
            sh "mvn package"
        }
    }

    container('docker') {
      stage('Build docker image') {
          sh """
            docker login ${registry} -u admin -p Harbor12345
            docker build -t spring-boot-rest:1.0 .
            docker build -t ${registry}/${image}:${shortGitCommit} .
            docker push ${registry}/${image}:${shortGitCommit}

            docker tag ${registry}/${image}:${shortGitCommit} ${registry}/${image}:latest
            docker push ${registry}/${image}:latest
            """
      }
    }

    container('helm') {
      withCredentials([file(credentialsId: 'harbor-crt', variable: 'FILE') ]) {
            stage('Release to helm repository') {
                sh """
                echo '${registry_ip} ${registry}' >> /etc/hosts
                cd k8s
                helm repo add --ca-file $FILE ${helm_repo} \
                --username=admin \
                --password=Harbor12345 ${helm_repo_url}
                helm push --ca-file $FILE ./${app}/ ${helm_repo}
                """
            }
        }
    }
  }
}
