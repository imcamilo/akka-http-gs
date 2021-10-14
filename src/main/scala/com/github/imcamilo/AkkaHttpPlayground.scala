package com.github.imcamilo

import akka.actor.ActorSystem
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest}
import akka.http.scaladsl.server.ContentNegotiator.Alternative.ContentType
import akka.stream.ActorMaterializer

import java.net.URLEncoder

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

  def sendRequest() = ???

  def main(args: Array[String]): Unit = {

  }

}
