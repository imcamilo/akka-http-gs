name := "akka-http-gs"

version := "0.1"

scalaVersion := "2.13.8"
val circeVersion = "0.12.3"
val akkaVersion = "2.6.5"
val akkaHttpVersion = "10.2.0"
val akkaHttpJsonSerializersVersion = "1.34.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "de.heikoseeberger" %% "akka-http-circe" % akkaHttpJsonSerializersVersion,
  "de.heikoseeberger" %% "akka-http-jackson" % akkaHttpJsonSerializersVersion
)