lazy val root = (project in file("."))
  .settings(
    name := "finatra-server",
    organization := "yasszu",
    version := "1.0",
    scalaVersion := "2.12.8",
    mainClass in assembly := Some("app.Main"),
    libraryDependencies ++= Seq(
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "com.typesafe" % "config" % "1.3.1",
      "com.twitter" %% "finagle-mysql" % "17.12.0",
      "com.twitter" %% "finatra-http" % "19.6.0",
      "io.getquill" %% "quill-finagle-mysql" % "3.2.0"
    )
  )

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs@_*) => MergeStrategy.discard
  case x => MergeStrategy.first
}
