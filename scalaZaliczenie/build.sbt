name := "scalaZaliczenie"
 
version := "1.0" 
      
lazy val `scalazaliczenie` = (project in file(".")).enablePlugins(PlayScala,PlayEbean)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
      
scalaVersion := "2.11.7"

libraryDependencies ++= Seq( "org.postgresql" % "postgresql" % "9.4-1206-jdbc42", jdbc  , ws , specs2 % Test, "org.mindrot" % "jbcrypt" % "0.3m" )

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

libraryDependencies += "com.h2database" % "h2" % "1.4.192"

