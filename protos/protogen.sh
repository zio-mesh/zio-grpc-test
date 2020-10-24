protos="./"
#zgrpc="0.4.0+23-d66c1d7d-SNAPSHOT"
zgrpc="0.4.0"
deps="/home/bku/.local/include/google/protobuf"

scalapbc -v3.13.0 \
--plugin-artifact=com.thesamet.scalapb.zio-grpc:protoc-gen-zio:$zgrpc:default,classifier=unix,ext=sh,type=jar \
-- $protos/*.proto --zio_out=./out --scala_out=grpc:./out \
-I$deps \
-I$protos 
