import org.scoverage.coveralls.Imports.CoverallsKeys._

lazy val root = (project in file(".")).
  settings(
    name := "distributed-tutorial-solution",
    version := "0.1.0",
    scalaVersion := "2.11.8",
    scalacOptions ++= Seq(
      "-unchecked",
      "-feature",
      "-deprecation",
      "-encoding", "UTF-8",
      "-Xfatal-warnings", // This may need to be commented out during intermediate development, but should be re-enabled before pushing
      "-Ywarn-dead-code",
      "-Ywarn-unused",
      "-Ywarn-unused-import"
    ),
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest"    % "3.0.0",
      "org.scalaz"    %% "scalaz-core"  % "7.2.5",
      "joda-time"     %  "joda-time"    % "2.9.4",
      "org.joda"      %  "joda-convert" % "1.7"
    ),
    // The `coverageEnabled` key is equivalent to running the `coverage` task before each `test` task
    coverageEnabled := true,
    coverallsToken := sys.env.get("COVERALLS_REPO_TOKEN")
  )

/** The `deployBuild` command is configured to run in CircleCI, so this alias determines the build task there. It should
  * generally not be used for local testing. Prefer the simple `test` task, or `;test;coverageReport` to view this
  * report without uploading to Coveralls.
  *
  * `coverageReport` builds the report used by Coveralls to track test coverage
  * `coverageAggregate` collects coverage data from sub-projects in a multi-project build (currently unused)
  * `coveralls` uploads the report to Coveralls. This requires `the coverallsToken` key to be set in the
  *   root project. This environment variable has been configured in
  */
addCommandAlias("deployBuild", ";clean;test;coverageReport;coverageAggregate;coveralls")
