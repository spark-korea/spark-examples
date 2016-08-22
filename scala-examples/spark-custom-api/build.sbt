name := "spark-custom-api"

version := "0.0.1-SNAPSHOT"

organization := "com.company"

scalaVersion := "2.11.7"

spName := "company/spark-custom-api"

crossScalaVersions := Seq("2.10.5", "2.11.7")

sparkVersion := "1.6.0"

val testSparkVersion = settingKey[String]("The version of Spark to test against.")

testSparkVersion := sys.props.get("spark.testVersion").getOrElse(sparkVersion.value)

sparkComponents := Seq("core", "sql")

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-api" % "1.7.5" % "provided",
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "com.novocode" % "junit-interface" % "0.9" % "test"
)

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % testSparkVersion.value % "test",
  "org.apache.spark" %% "spark-sql" % testSparkVersion.value % "test",
  "org.scala-lang" % "scala-library" % scalaVersion.value % "compile"
)

// This is necessary because of how we explicitly specify Spark dependencies
// for tests rather than using the sbt-spark-package plugin to provide them.
spIgnoreProvided := true

publishMavenStyle := true

spAppendScalaVersion := true

spIncludeMaven := true

pomExtra := (
  <url>https://github.com/HyukjinKwon/spark-custom-api</url>
  <scm>
    <url>git@github.com:HyukjinKwon/spark-custom-api.git</url>
    <connection>scm:git:git@github.com:HyukjinKwon/spark-custom-api.git</connection>
  </scm>
  <developers>
    <developer>
      <id>hyukjinkwon</id>
      <name>Hyukjin Kwon</name>
      <url>https://www.facebook.com/hyukjin.kwon.96</url>
    </developer>
  </developers>)

parallelExecution in Test := false

// Skip tests during assembly
test in assembly := {}

ScoverageSbtPlugin.ScoverageKeys.coverageHighlighting := {
  if (scalaBinaryVersion.value == "2.10") false
  else true
}

