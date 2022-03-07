ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "scala-task-boilerplate",
    libraryDependencies += "org.apache.kafka" % "kafka-clients" % "2.0.0"
  )
