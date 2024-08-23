pipeline{

    // will find any available agent
    agent any

    // Setup environment to pull from the jenkins global for the credentials
    environment{
        // Global Variables from Jenkins
        dockerHub = credentials('dockerHub')
        DBPASS = credentials('DBPASS')

        // Custom Variables for this Jenkinsfile only
        SERVICE = "byteshare-back" // #CHECK CONFIG
        VERSION = "${env.BUILD_ID}"
        IMAGE = "${dockerHub_USR}/${SERVICE}:${VERSION}"
        CONTAINER = "${SERVICE}-service"
    }

    // definte all of the stages
    stages{

        // First stage is the build & deliver and name it
        stage('Build & Deliver'){

            // Steps are all of the executables on shell, use "" when using environment variables
            steps{
                sh "docker login -u ${dockerHub_USR} -p ${dockerHub_PSW}"
                sh "docker build -t ${IMAGE} ."
                //pushes image to dockerHub
                sh "docker push ${IMAGE}"
            }

        }

        // stage to stop all running contianers & remove them
        stage("Stop & Destroy"){
            steps{
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE'){
                    sh "docker stop ${CONTAINER}"
                    sh "docker rm ${CONTAINER}"
                }
            }
        }

        stage('Deploy'){

            steps{
            // docker run, but we've add the -e flag to pass the environment variable defined in Jenkins to the our Docker Container
            // so our application.yml has context for what's there
                sh "docker run --name ${CONTAINER} -e DBPASS=${DBPASS} -d -p 8888:9999 ${IMAGE}" // #CHECK CONFIG ports
            }

        }

    }


}