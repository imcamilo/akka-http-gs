package com.github.imcamilo

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest}
import akka.stream.ActorMaterializer

import java.net.URLEncoder
import scala.concurrent.duration.DurationInt

object AkkaHttpPlayground {

  implicit val system = ActorSystem() //akka actors
  implicit val materializer = ActorMaterializer() //akka streams

  import system.dispatcher //thread pool

  val source =
    """
      |object SimpleApp {
      |  val firstField = 2
      |
      |  def firstMethod(x: Int) = x + 1
      |
      |  def main(args: Array[String]): Unit = println(firstField)
      |}
      |""".stripMargin

  val request = HttpRequest(
    method = HttpMethods.POST,
    uri = "http://markup.su/api/highlighter",
    entity = HttpEntity(
      ContentTypes.`application/x-www-form-urlencoded`,
      s"source=${URLEncoder.encode(source.trim, "UTF-8")}&language=Scala&theme=Sunburst"
    )
  )

  def simpleRequest() = {
    val responseFuture = Http().singleRequest(request)
    responseFuture.flatMap(_.entity.toStrict(2 seconds)).map(_.data.utf8String).foreach(println)
  }

  def main(args: Array[String]): Unit = {

  }

}
