plugins {
    `java-library`

    `maven-publish`

    id("com.github.hierynomus.license")

    id("com.github.johnrengelman.shadow")
}

license {
    header = rootProject.file("LICENSE")
    encoding = "UTF-8"

    mapping("java", "JAVADOC_STYLE")

    include("**/*.java")
}

//java {
//    toolchain.languageVersion.set(JavaLanguageVersion.of(project.properties["java_version"].toString()))
//}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

//tasks {
//    compileJava {
//        options.release.set(project.properties["java_version"].toString().toInt())
//    }
//}