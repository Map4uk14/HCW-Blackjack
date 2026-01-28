plugins {
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
}

repositories {
    mavenCentral()
}

javafx {
    version = "21"
    modules = listOf("javafx.controls", "javafx.fxml")
}

application {
    mainClass.set("com.blackjack.view.BlackjackApp")
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
}