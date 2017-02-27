name := "microservice"
version := "0.1.0"
scalaVersion := "2.12.1"
val finagleVersion = "6.42.0"
resolvers += "twitter" at "https://maven.twttr.com"
libraryDependencies ++= Seq( 
  "com.twitter" %% "finagle-http" % finagleVersion,
  "com.twitter" %% "finagle-stats" % finagleVersion
)
