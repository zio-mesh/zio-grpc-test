dir='../../protos/'

protoc -I=$dir $dir/mood.proto \
--js_out=import_style=commonjs:. \
--grpc-web_out=import_style=commonjs,mode=grpcwebtext:.
