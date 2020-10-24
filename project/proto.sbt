resolvers += Resolver.sonatypeRepo("snapshots")
val zioGrpcVersion = "0.4.0+23-d66c1d7d-SNAPSHOT"

addSbtPlugin("com.thesamet" % "sbt-protoc" % "1.0.0-RC2")

libraryDependencies ++= Seq(
  "com.thesamet.scalapb.zio-grpc" %% "zio-grpc-codegen" % zioGrpcVersion
)
