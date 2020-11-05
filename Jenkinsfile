def label = "worker-${UUID.randomUUID().toString()}"

podTemplate(label: label, containers: [
  containerTemplate(name: 'maven', image: 'maven:3.6.3-adoptopenjdk-8', command: 'cat', ttyEnabled: true),
  containerTemplate(name: 'jnlp', image: 'jenkinsci/jnlp-slave', args: '${computer.jnlpmac} ${computer.name}', envVars: [ 
      envVar(key: 'JENKINS_URL', value: 'http://jenkins.jenkins.svc.cluster.local:8080')
    ]
    )
],
volumes: [
  hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock')
]) {
  node(label) {
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
  }
}
