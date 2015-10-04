

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.actor.Props
import akka.http.scaladsl.Http
import akka.actor.ActorRef
import scala.concurrent.ExecutionContext
import akka.stream.Materializer

import java.net.InetAddress
import akka.http.scaladsl.unmarshalling.PredefinedFromEntityUnmarshallers
import akka.http.scaladsl.unmarshalling.Unmarshaller

object WebServer extends App {

  private val initial: String => Any = _ match {
    case "Lights"  => new Lights(Color.RED, GoodBad.BAD, 28.5F, true)
    case "Buttons" => new Buttons(true, false)
    case _@ name   => name
  }

  private def route = {

    import akka.http.scaladsl.server.Directives._
    
    val gson = new com.google.gson.Gson()

    val web = getFromDirectory(".") ~ getFromDirectory("src/main/html") ~ getFromResourceDirectory("web")

    path("status" / Segment) { name =>
      complete(
        ReactJsonGenerator(initial(name)))
    } ~
      path("apply" / Segment) { name =>
        post {
          entity(as[String]) { s =>
            import com.google.gson.Gson
            println(gson.fromJson(s, Class.forName(name)));
            complete("done")
          }
        }
      } ~ web
  }

  implicit val system = ActorSystem("Sys")
  implicit val mat = ActorMaterializer()

  Http().bindAndHandle(route, InetAddress.getLocalHost.getHostName, 9090)

}