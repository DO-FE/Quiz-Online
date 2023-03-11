#!groovy?
pipeline{
    agent{
        docker {
            image 'axyres/maven:3.8.6-aws-17'
            args '--user=0 -v $WORKSPACE:/tmp/sbapp -w /temp/sbapp'
            reuseNode true
        }
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '8', artifactNumToKeepStr: '8', daysToKeepStr: '7', artifactDaysToKeepStr: '7'))
    }
    environment {
        Version="1.0-SNAPSHOT"
        PJNAME="QuizOnline"
        Branch="production"
        CredentialID_GitHub=""
        CredentialID_Nexus=""
        ChatID_Telegram=""
        gitRepo="https://github.com/DO-FE/Quiz-Online.git"
        URL_Nexus=""
        Repository_Nexus=""
    }
    stages{
        stage('Stage 1 Getting build number'){
            steps{
                script{
                    def now = new Date()
					buildVersion =  now.format("yyyy.MM.dd.HHmm", TimeZone.getTimeZone('ICT'))
					currentBuild.displayName = "${buildVersion}"
                }
            }
        }
        stage('Stage 2 Checkout Code'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: "$Branch"]], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'CheckoutOption', timeout: 90], [$class: 'CloneOption', depth: 0, noTags: false, reference: '', shallow: false, timeout: 90], [$class: 'SubmoduleOption', disableSubmodules: false, parentCredentials: true, recursiveSubmodules: true, reference: '', timeout: 90, trackingSubmodules: true]], gitTool: 'Default', submoduleCfg: [], userRemoteConfigs: [[credentialsId: "$CredentialID_GitHub", url: "$gitRepo"]]])
            }
        }
        stage('Stage 3 Get commit harsh'){
            steps{
                sh"""
                    cd ${WORKSPACE}/
                    echo "${PJNAME}" > ${WORKSPACE}/commit.txt
                    /bin/git log -1 --pretty=format:'%h, %an, %ar, %aD, %s' >> ${WORKSPACE}/commit.txt
					echo "\n############################################" >> ${WORKSPACE}/commit.txt

                """
            }
        }
        stage('Stage 4 Build Code'){
            steps{
                script{
                    sh"""
                        echo 'Start Build Code'
                        mvn clean install -DskipTests=true -U -Dversion=${Version}

                        echo 'End Build Code'
                    """
                }
            }
        }
        stage('Stage 5 Upload to Nexus Repository') {
            steps{
                script{
                    withCredentials([usernamePassword(credentialsId: "$CredentialID_Nexus", passwordVariable:'ARTIFACTORY_PASSWORD', usernameVariable:'ARTIFACTORY_USER')]) {
                        sh"""
                            echo Start Push pakage to Artifactory ......

                            curl -v -u "${ARTIFACTORY_USER}:${ARTIFACTORY_PASSWORD}" --upload-file "${WORKSPACE}/target/${PJNAME}-${Version}.war" "${URL_Nexus}/${Repository_Nexus}/admin/${PJNAME}/${Branch}/latest/${PJNAME}-${Version}.war"
                            curl -v -u "${ARTIFACTORY_USER}:${ARTIFACTORY_PASSWORD}" --upload-file "${WORKSPACE}/target/${PJNAME}-${Version}.war" "${URL_Nexus}/${Repository_Nexus}/admin/${PJNAME}/${Branch}/${buildVersion}/${PJNAME}-${Version}.war"

                            echo End Upload package ......
                        """
                    }
                }
            }
        }
        stage('Stage 6 Trigger build job image') {
            steps {
                script {
                    build job: "Build-Image", parameters: [
                        string(name: "buildVersion", value: "${buildVersion}"),
                        string(name: "buildBranch", value: "${Branch}"),
                        string(name: "PJNAME", value: "${PJNAME}"),
                        string(name: "Version", value: "${Version}"),
                        string(name: "Nexus_URL", value: "${URL_Nexus}"),
                        string(name: "Repository_Nexus", value: "${Repository_Nexus}"),
                        booleanParam(name: 'PJ_admin', value: 'true'),
                        booleanParam(name: 'Start_container', value: 'true'),
                        booleanParam(name: 'Push_images_to_Hub', value: 'true')
                    ],
                    wait: true
                }
            }
        }
    }
    post{
        always{
            echo 'One way or another, I have finished'
        }
        success {
            echo 'I success'
            script {
                def commitMsg = readFile "${WORKSPACE}/commit.txt"
                telegramSend (message: """[BUILD SUCCESS] - PROJECT: $PJNAME - BRANCH: $Branch - VERSION: $buildVersion - COMMIT MESSAGE: $commitMsg""", chatId: "${ChatID_Telegram}")
            }
            deleteDir()
        }
        failure {
            echo 'I failure'
            script{
                def commitMsg = readFile "${WORKSPACE}/commit.txt"
                telegramSend (message: """[BUILD FAILED] - PROJECT: $PJNAME - BRANCH: $Branch - VERSION: $buildVersion - COMMIT MESSAGE: $commitMsg""", chatId: "${ChatID_Telegram}")
            }
            deleteDir()
        }
    }
}
String determineRepoName() {
    return scm.getUserRemoteConfigs()[0].getUrl().tokenize('/').last().split("\\.")[0]
}