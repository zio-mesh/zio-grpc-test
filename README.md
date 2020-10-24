# zio-grpc-test

This aims to build a `zio-grpc` project sendbox and reproduce possible bugs

## Bug 1: Loosing items in a stream for Enum Stream response

### Test setup

Ubuntu 18.04.5 <br>
OpenJDK Runtime Environment (build 11.0.10-testing+0-builds.shipilev.net-openjdk-jdk11-dev-b350-20201014) <br>
Scala 2.13.3 <br>
Protoc 3.13.0 <br>
grpcurl v1.7.0 <br>

### Service description

```scala
service MoodService {
  rpc checkMood(google.protobuf.Empty) returns (stream MoodResp);
}

message MoodResp {
  enum Mood {
    HAPPY = 0;
    SAD = 1;
  }
  Mood mood = 1;
  string desc = 2;
}
```

### Reproduce

```bash
> sbt reStart
> grpcurl -plaintext localhost:9090 MoodService/checkMood
```

### Current result for Enum:

```shell
bku@lap:~/work/ana/icare-back/it$ grpcurl -plaintext localhost:9090 MoodService/checkMood
bku@lap:~$ grpcurl -plaintext localhost:9090 MoodService/checkMood
{
  "desc": "good"
}
{
  "mood": "SAD",
  "desc": "bad"
}
```

In this test, I see some output, but for my prod env, I see zero output for `enum` fields
