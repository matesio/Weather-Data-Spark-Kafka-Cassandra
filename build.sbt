
scalaVersion := "2.11.8"

name := "hello-world"
organization := "ch.epfl.scala"
version := "1.0"

val kafkaVersion = "0.11.0.0"


resolvers += "Big Bee Consultants" at "http://dl.bintray.com/rick-beton/maven"

libraryDependencies += "org.apache.kafka" % "kafka-clients" % kafkaVersion
//resolvers += Resolver.mavenLocal
libraryDependencies +="org.slf4j" % "slf4j-api" % "1.7.1"
libraryDependencies +="ch.qos.logback" % "logback-classic" % "1.0.3"


libraryDependencies ++= Seq(
    "uk.co.bigbeeconsultants" %% "bee-client" % "0.29.+",
    "org.json4s" %% "json4s-native" % "3.2.9",
    "co.freeside" % "betamax" % "1.1.2" % "test",
    "org.codehaus.groovy" % "groovy-all" % "1.8.8" % "test"
)
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.4",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.4" % Test
)
// https://mvnrepository.com/artifact/au.com.bytecode/opencsv
//libraryDependencies += "au.com.bytecode" % "opencsv" % "2.4"

