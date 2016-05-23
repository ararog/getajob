package br.net.bmobile.getajob

import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import br.net.bmobile.getajob.http.HttpService
import br.net.bmobile.getajob.utils.{Config, Migration}

import scala.concurrent.ExecutionContext

object Main extends App with Config with HttpService with Migration {
  private implicit val system = ActorSystem()

  override protected implicit val executor: ExecutionContext = system.dispatcher
  override protected val log: LoggingAdapter = Logging(system, getClass)
  override protected implicit val materializer: ActorMaterializer = ActorMaterializer()

  migrate()

  Http().bindAndHandle(routes, httpInterface, httpPort)
}
