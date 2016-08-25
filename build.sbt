val root = (project in file(".")).
  settings(
    name := "distributed-tutorial-solution",
    version := "0.1.0",
    scalaVersion := "2.11.8",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest"   % "3.0.0",
      "org.scalaz"    %% "scalaz-core" % "7.2.5",
      "joda-time"     %  "joda-time"   % "2.9.4"
    ),
    coverageEnabled := true
  )
