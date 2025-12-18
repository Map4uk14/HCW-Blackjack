plugins {
    id 'application'
    id 'org.openjfx.javafxplugin' version '0.1.0'
}

repositories {
    mavenCentral()
}

javafx {
    version = "17"
    modules = [ 'javafx.controls', 'javafx.fxml' ]
}

application {
    mainClass = 'com.blackjack.view.BlackjackApp'
}

dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter:5.8.1'
}