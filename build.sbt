name := "ScalaExperiment"

version := "0.1"

scalaVersion := "2.11.8"

val testcontainersScalaVersion = "0.36.0"

libraryDependencies ++= Seq(
   "com.dimafeng" %% "testcontainers-scala-scalatest" % testcontainersScalaVersion % "test",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test",
  "com.dimafeng" %% "testcontainers-scala-mysql" % testcontainersScalaVersion % "test",
   "mysql" % "mysql-connector-java" % "8.0.19"
)