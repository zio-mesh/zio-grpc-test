package grpc.zio.test
import mood.ZioMood._
import zio.{ ZEnv }

import scalapb.zio_grpc.{ ServerMain, ServiceList }
import com.google.protobuf.empty.Empty
import io.grpc.Status
import mood.MoodResp
import zio.stream.ZStream
import mood.MoodResp.Mood._

class MoodServiceImpl() extends RMoodService[ZEnv] {

  override def checkMood(request: Empty): ZStream[zio.ZEnv with Any, Status, MoodResp] =
    ZStream(MoodResp(HAPPY, "good"), MoodResp(SAD, "bad"), MoodResp(HAPPY, "happy again :)"))

}

object Server extends ServerMain {

  val moodService = new MoodServiceImpl()

  // Launch the server
  override def port: Int = 9090

  override def services = ServiceList.add(moodService)
}
