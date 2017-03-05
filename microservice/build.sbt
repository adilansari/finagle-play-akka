name := "microservice"
version := "0.1.0"
scalaVersion := "2.12.1"
val finagleVersion = "6.42.0"
resolvers += "twitter" at "https://maven.twttr.com"
libraryDependencies ++= Seq( 
  "com.twitter" %% "finagle-http" % finagleVersion,
  "com.twitter" %% "finagle-stats" % finagleVersion,
  "com.softwaremill.macwire" %% "macros" % "2.3.0" % "provided",
  "com.softwaremill.macwire" %% "util" % "2.3.0"
)
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}