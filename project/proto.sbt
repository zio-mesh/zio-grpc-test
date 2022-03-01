resolvers += Resolver.sonatypeRepo("snapshots")
val zioGrpcVersion = "0.5.0"

addSbtPlugin("com.thesamet" % "sbt-protoc" % "1.0.2")

libraryDependencies ++= Seq(
  "com.thesamet.scalapb.zio-grpc" %% "zio-grpc-codegen" % zioGrpcVersion
)
