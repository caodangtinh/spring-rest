def label = "worker-${UUID.randomUUID().toString()}"

podTemplate(label: label, containers: [
  containerTemplate(name: 'docker', image: 'docker', command: 'cat', ttyEnabled: true),
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
    def shortGitCommit = sh(script: "printf \$(git rev-parse --short HEAD)",returnStdout: true)
    def image = "backbase/spring-rest"
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
  }
}
