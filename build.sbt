import sbt._
import Defaults._

organization := "com.twitter"

name := "sbt-gitflow"

version := "0.1.0"

resolvers += Resolver.url(
  "sbt-plugin-releases",
  new URL("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/")
)(Resolver.ivyStylePatterns)

sbtPlugin := true

// Publishing options:
publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { x => false }

libraryDependencies += sbtPluginExtra(
  m = "com.github.gseitz" % "sbt-release" % "0.8", // Plugin module name and version
  sbtV = "0.13.0",    // SBT version
  scalaV = "2.10"    // Scala version compiled the plugin
)

credentials += Credentials(Path.userHome / ".m2" / "nexus-credentials")

publishTo <<= version { (v: String) =>
  val nexus = "http://repository.ba-group.fi/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots/")
  else
    Some("snapshots" at nexus + "content/repositories/releases/")
}

pomExtra := <url>https://github.com/sritchie/sbt-gitflow</url>
  <licenses>
    <license>
      <name>Apache 2</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
      <distribution>repo</distribution>
      <comments>A business-friendly OSS license</comments>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:sritchie/sbt-gitflow.git</url>
    <connection>scm:git:git@github.com:sritchie/sbt-gitflow.git</connection>
  </scm>
  <developers>
    <developer>
      <id>sritchie</id>
      <name>Sam Ritchie</name>
      <url>http://twitter.com/sritchie</url>
    </developer>
  </developers>
