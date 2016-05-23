name := "getajob"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.11" % "2.2.6",
  "org.scala-lang" % "scala-reflect" % "2.11.8",
  "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.4",
  "com.github.mauricio" %% "postgresql-async" % "0.2.19",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "org.mindrot" % "jbcrypt" % "0.3m",
  "org.flywaydb" % "flyway-core" % "4.0",
  "com.sksamuel.elastic4s" % "elastic4s-core_2.11" % "2.2.1",
  "com.typesafe.akka" % "akka-actor_2.11" % "2.4.2",
  "com.typesafe.akka" % "akka-http-core_2.11" % "2.4.2",
  "com.typesafe.akka" % "akka-testkit_2.11" % "2.4.2",
  "com.typesafe.slick" % "slick_2.11" % "3.1.1",
  "com.typesafe.akka" % "akka-http-spray-json-experimental_2.11" % "2.4.2",
  "de.choffmeister" %% "auth-common" % "0.2.0",
  "de.choffmeister" %% "auth-akka-http" % "0.2.0",
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41"
)
