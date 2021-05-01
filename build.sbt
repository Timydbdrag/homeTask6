name := "kafka_client"

version := "0.1"

scalaVersion := "2.13.4"
val circeVersion = "0.14.0-M4"

libraryDependencies ++= Seq(
  "org.apache.kafka" % "kafka-clients" % "2.6.0",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.4.0",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "org.apache.commons" % "commons-csv" % "1.8",
  "io.circe" %% "circe-generic-extras" % "0.13.1-M4",
  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion,
  "io.circe" %% "circe-literal" % circeVersion
)
