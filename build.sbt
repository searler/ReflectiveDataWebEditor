
name := "ReflectiveDataWebEditor"

version := "0.1"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.12",
  "com.typesafe.akka" % "akka-http-experimental_2.11" % "1.0",
  "com.google.code.gson" % "gson" % "2.3.1"
)


fork in run := true
