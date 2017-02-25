import com.twitter.finagle.{Http, Service, http}
import com.twitter.util.{Await, Future}

object Client extends App {
  val host = "www.scala-lang.org"
  val client: Service[http.Request, http.Response] = Http.newService(host + ":80")
  val request = http.Request(http.Method.Get, "/")
  request.host = host
  val response : Future[http.Response] = client(request)
  Await.result(response.onSuccess {
    rep: http.Response => println("GET success: " + rep)
  })
}
