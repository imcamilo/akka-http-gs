package com.github.imcamilo

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

import java.util.UUID

//spray 1
import spray.json._

case class Person(name: String, age: Int)

case class UserAdded(id: String, timestamp: Long)

//spray 2
trait PersonJsonProtocol extends DefaultJsonProtocol {
  implicit val personFormat = jsonFormat2(Person)
  implicit val userAddedFormat = jsonFormat2(UserAdded)
}

//spray 3
object AkkaHttpJson extends PersonJsonProtocol with SprayJsonSupport {

  implicit val system = ActorSystem(Behaviors.empty, "AkkaHttpJSON")

  val route: Route = (path("api" / "users") & post) {
    entity(as[Person]) { person: Person =>
      complete(UserAdded(UUID.randomUUID.toString, System.currentTimeMillis()))
    }
    //complete("Yeah, roger that")
  }

  def main(args: Array[String]): Unit = {
    Http().newServerAt("localhost", 8766).bind(route)
  }

}
