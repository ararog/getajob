package br.net.bmobile.getajob.http.routes

import akka.event.LoggingAdapter
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.stream.ActorMaterializer
import akka.util.Timeout
import br.net.bmobile.getajob.utils.{Config, Protocol}

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext

trait BaseServiceRoute extends Protocol with SprayJsonSupport with Config {
  protected implicit def executor: ExecutionContext
  protected implicit def materializer: ActorMaterializer
  protected def log: LoggingAdapter
  protected implicit val timeout = Timeout(5 seconds)
}
