// vars/deployApp.groovy
def call(Map config) {
    script {
        echo "Deploying image: ${config.imageName} to port ${config.port}"

        sh "docker pull ${config.imageName}"
        sh "docker ps -a --filter 'name=${config.containerName}' -q | xargs -r docker stop"
        sh "docker ps -a --filter 'name=${config.containerName}' -q | xargs -r docker rm"
        sh "docker run -d --name ${config.containerName} -p ${config.port}:3000 ${config.imageName}"
    }
}