import com.twitter.finagle.{Http, Service, http}
import com.twitter.util.Await

object Proxy extends App {

  val client: Service[http.Request, http.Response] = Http.newService("twitter.com:80")

  val server = Http.serve(":8080", client)
  Await.ready(server)
}
