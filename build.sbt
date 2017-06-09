name := "aria2-agent"

version := "1.0"

scalaVersion := "2.12.2"

javacOptions ++= Seq("-encoding", "UTF-8")

lazy val aria2Agent = ProjectRef(file("."), "aria2-agent")

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-api" % "1.7.23",
  "com.typesafe" % "config" % "1.3.1",
  "org.apache.commons" % "commons-lang3" % "3.5",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.8.7",
  "org.asynchttpclient" % "async-http-client" % "2.0.31",
  "javax.validation" % "validation-api" % "1.1.0.Final"
)

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-simple" % "1.7.23" % "test",
  "org.junit.jupiter" % "junit-jupiter-api" % "5.0.0-M4" % "test",
  "org.mockito" % "mockito-core" % "2.8.9" % "test"
)
