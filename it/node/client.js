// const {
//   HelloRequest,
//   RepeatHelloRequest,
//   HelloReply,
// } = require("./mood_pb.js");

//var grpc = require('@grpc/grpc-js');
//var protoLoader = require('@grpc/proto-loader');
//var packageDefinition = protoLoader.loadSync(
//    PROTO_PATH,
//    {keepCase: true,
//     longs: String,
//     enums: String,
//     defaults: true,
//     oneofs: true
//    });
//var protoDescriptor = grpc.loadPackageDefinition(packageDefinition);
//var helloworld = protoDescriptor.helloworld;

const { MoodServiceClient } = require("./mood_grpc_web_pb.js");

var client = new MoodServiceClient(
  "http://localhost:8080",
  null,
  null
);

// simple unary call
// var request = new HelloRequest();
// request.setName("World");

// client.checkMood({}, {}, (err, resp) => {
//   if (err) {
//     console.log(
//       `Unexpected error for checkMood: code = ${err.code}` +
//         `, message = "${err.message}"`
//     );
//   } else {
//     console.log(resp.getMessage());
//   }
// });

// // server streaming call
// var streamRequest = new RepeatHelloRequest();
// streamRequest.setName("World");
// streamRequest.setCount(5);

var stream = client.checkMood({}, {});
stream.on("data", (resp) => {
  console.log(resp.getMessage());
});
stream.on("error", (err) => {
  console.log(
    `Unexpected stream error: code = ${err.code}` +
      `, message = "${err.message}"`
  );
});
