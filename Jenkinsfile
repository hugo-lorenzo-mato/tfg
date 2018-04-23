#!groovy

/**
 *  Jenkinsfile: catalogador-tfg - Pipeline produccion
 *  @hugo-lorenzo-mato
 */

    def appName = 'catalogador-tfg'
    def nodeImage = 'node:6.14.1-alpine'
    def gradleImage = 'gradle:4.6.0-jdk8'
    def mavenChromeImage = 'markhobson/maven-chrome:latest'
    def flywayImage = 'boxfuse/flyway:5'

    def db = null
    def dbHostname = null
    def dbName = 'postgres'
    def dbUser = 'postgres'
    def dbPassword = 'postgres'

    def web = null
    def webHostname = null

    node () {
    try {
    stage ('Git: Checkout'){
    echo 'Checking out git repository'
    sh "git config --global credential.helper 'cache --timeout=3600'"
    checkout scm
    }

    docker.image(nodeImage).inside("-u 0:0 --network public") {
    stage ('Node: Instalando dependencias') {
    sh "cd appserver/application && npm install"
    }
    stage ('Gradle: Install') {
    sh "cd appserver/application && gradle run build"
    }
    }

    docker.image(gradleImage).inside("-u 0:0 --network public"){
    stage('Gradle: Compilando'){
    sh"cd appserver/application && gradle war"
    }
    }



    } finally {
    sh "echo 'saliendo'"
    }
    }


    node () {
    stage ('Test: Install') {
    sh "echo 'TESTEANDO'"
    }
    }


