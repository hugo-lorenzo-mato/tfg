#!groovy

/**
 *  Jenkinsfile: catalogador-tfg - Pipeline produccion
 *  @hugo-lorenzo-mato
 */

def appName = 'catalogador-tfg'
def builderImage = 'maven:3.5'
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
         sh "echo 'Bien hecho'"
    }
}


node () {
     stage ('Test: Install') {
         sh "echo 'TESTEANDO'"
    }
}
