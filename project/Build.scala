import sbt._
import Keys._

object BuildSettings {
  val paradiseVersion = "2.0.1"
  val buildSettings = Defaults.defaultSettings ++ Seq(
    organization := "org.scalamacros",
    version := "1.0.0",
    scalacOptions ++= Seq(),
    scalaVersion := "2.11.1",
    crossScalaVersions := Seq("2.10.2", "2.10.3", "2.10.4", "2.11.0", "2.11.1"),
    resolvers += Resolver.sonatypeRepo("snapshots"),
    resolvers += Resolver.sonatypeRepo("releases"),
    addCompilerPlugin("org.scalamacros" % "paradise" % paradiseVersion cross CrossVersion.full)
  )
}

object MyBuild extends Build {
  import BuildSettings._

  lazy val root: Project = Project(
    "root",
    file("."),
    settings = buildSettings ++ Seq(
      run <<= run in Compile in core
    )
  ) aggregate(macros, core)

  lazy val macros: Project = Project(
    "macros",
    file("macros"),
    settings = buildSettings ++ Seq(
      libraryDependencies <+= (scalaVersion)("org.scala-lang" % "scala-reflect" % _),
      libraryDependencies += "lego-core" % "lego-core_2.11" % "0.1-SNAPSHOT",
      libraryDependencies += "lego-compiler" % "lego-compiler_2.11" % "0.1-SNAPSHOT",
      libraryDependencies += "root" % "root_2.11" % "0.1-SNAPSHOT",
      libraryDependencies += "core" % "core_2.11" % "0.1-SNAPSHOT",
      libraryDependencies += "macros" % "macros_2.11" % "0.1-SNAPSHOT",
      libraryDependencies ++= (
        if (scalaVersion.value.startsWith("2.10")) List("org.scalamacros" %% "quasiquotes" % paradiseVersion)
        else Nil
      )
    )
  )

  lazy val core: Project = Project(
    "core",
    file("core"),
    settings = buildSettings
  ) dependsOn(macros)
}
