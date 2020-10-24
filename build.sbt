inThisBuild(
  List(
    resolvers ++= Seq(
      Resolver.mavenLocal,
      Resolver.sonatypeRepo("releases"),
      Resolver.sonatypeRepo("snapshots")
    ),
    // semanticdbEnabled := true,
    scalaVersion := "2.13.3",
    maxErrors := 3,
    organization := "org.icare",
    startYear := Some(2020),
    licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
    developers := List(
      Developer(
        "tampler",
        "Boris V.Kuznetsov",
        "socnetfpga@gmail.com",
        url("https://github.com/tampler")
      )
    ),
    testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework"))
  )
)

lazy val commonSettings = Seq(
// Refine scalac params from tpolecat
  scalacOptions --= Seq(
    "-Xfatal-warnings"
  ),
  addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1")
)

lazy val zioDeps = libraryDependencies ++= Seq(
  "dev.zio" %% "zio"          % Version.zio,
  "dev.zio" %% "zio-streams"  % Version.zio,
  "dev.zio" %% "zio-test"     % Version.zio % "test",
  "dev.zio" %% "zio-test-sbt" % Version.zio % "test"
)

lazy val grpcDeps = libraryDependencies ++= Seq(
  "io.grpc"               % "grpc-netty"           % Version.grpc,
  "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion,
  "com.thesamet.scalapb" %% "scalapb-runtime"      % scalapb.compiler.Version.scalapbVersion % "protobuf"
)

// services
lazy val root = (project in file("."))
  .settings(
    name := "top",
    commonSettings,
    zioDeps,
    grpcDeps
  )

// Protobuf root settings
PB.protocVersion := Version.protoc
PB.protocExecutable := file("/home/bku/.local/bin/protoc")
PB.protoSources in Compile := Seq((baseDirectory in ThisBuild).value / "protos")
PB.targets in Compile := Seq(
  scalapb.gen(grpc = true)          -> (sourceManaged in Compile).value,
  scalapb.zio_grpc.ZioCodeGenerator -> (sourceManaged in Compile).value
)

// Aliases
addCommandAlias("rel", "reload")
addCommandAlias("com", "all compile test:compile it:compile")
addCommandAlias("fix", "all compile:scalafix test:scalafix")
addCommandAlias("fmt", "all scalafmtSbt scalafmtAll")

scalafixDependencies in ThisBuild += "com.nequissimus" %% "sort-imports" % "0.5.4"
