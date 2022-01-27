package com.github.imcamilo

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.{Failure, Success}

object AkkaHttpPlayground {

  implicit val system: ActorSystem = ActorSystem() //akka actors
  implicit val materializer: ActorMaterializer = ActorMaterializer() //akka streams

  import system.dispatcher //thread pool

  val post: String =
    """
      |{
      |    "title": "title sample",
      |    "body": "human body",
      |    "userId": 8766
      |}
      |""".stripMargin
  val uri = "https://jsonplaceholder.typicode.com/posts"
  val entity = HttpEntity(ContentTypes.`application/json`, post)

  val request: HttpRequest = HttpRequest(
    method = HttpMethods.POST,
    uri = uri,
    entity = entity
  )

  def sendRequest(): Future[String] = {
    val responseFuture: Future[HttpResponse] = Http().singleRequest(request)
    responseFuture.flatMap(_.entity.toStrict(2.seconds)).map(_.data.utf8String)
  }

  def main(args: Array[String]): Unit = {
    sendRequest().onComplete {
      case Success(value) => println(value)
      case Failure(e) => println(e.getMessage)
    }
  }

}
