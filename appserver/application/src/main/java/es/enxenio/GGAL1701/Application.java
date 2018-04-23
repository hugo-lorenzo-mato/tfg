package es.enxenio.GGAL1701;

import es.enxenio.GGAL1701.config._configBasePackage;
import es.enxenio.GGAL1701.config.util.Constants;
import es.enxenio.GGAL1701.config.util.MyProperties;
import es.enxenio.GGAL1701.controller._controllersBasePackage;
import es.enxenio.GGAL1701.model._modelBasePackage;
import es.enxenio.GGAL1701.util._utilBasePackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;

@ComponentScan(basePackageClasses = {_configBasePackage.class, _modelBasePackage.class,
    _controllersBasePackage.class, _utilBasePackage.class})
@EnableConfigurationProperties({MyProperties.class})
@EnableScheduling
@EnableAutoConfiguration
public class Application {

    // Perfil por defecto: Producción.
    // Leer README para cambiar el perfil a desarrollo.

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Inject
    private Environment env;

    /**
     * Main method, used to run the application.
     *
     * @param args the command line arguments
     * @throws UnknownHostException if the local host name could not be resolved into an address
     */
    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(Application.class);
        SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
        addDefaultProfile(app, source);
        Environment env = app.run(args).getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                "Application '{}' is running! Access URLs:\n\t" +
                "Local: \t\thttp://127.0.0.1:{}\n\t" +
                "External: \thttp://{}:{}\n----------------------------------------------------------",
            env.getProperty("spring.application.name"),
            env.getProperty("server.port"),
            InetAddress.getLocalHost().getHostAddress(),
            env.getProperty("server.port"));
    }

    /**
     * If no profile has been configured, set by default the "dev" profile.
     */
    private static void addDefaultProfile(SpringApplication app, SimpleCommandLinePropertySource source) {
        if (!source.containsProperty("spring.profiles.active") &&
            !System.getenv().containsKey("SPRING_PROFILES_ACTIVE")) {

            app.setAdditionalProfiles(Constants.SPRING_PROFILE_PRODUCTION);
        }
    }

    @PostConstruct
    public void initApplication() {
        if (env.getActiveProfiles().length == 0) {
            log.warn("No Spring profile configured, running with default configuracion");
        } else {
            log.info("Running with Spring profile(s) : {}", Arrays.toString(env.getActiveProfiles()));
            Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
            if (activeProfiles.contains(Constants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(Constants.SPRING_PROFILE_PRODUCTION)) {
                log.error("You have misconfigured your application! " +
                    "It should not run with both the 'dev' and 'prod' profiles at the same time.");
            }
        }
    }
}





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
