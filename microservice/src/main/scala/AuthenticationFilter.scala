import com.twitter.finagle.{Filter, Service}
import com.twitter.finagle.http.{Request, Response,Status}
import com.twitter.util.Future
import data.UserData
import org.jboss.netty.handler.codec.http.HttpHeaders.Names

class AuthenticationFilter(loginService: LoginService) extends Filter[Request, Response, AuthorizedRequest, Response] {

  override def apply(request: Request, continue: Service[AuthorizedRequest, Response]): Future[Response] = {
    request.headerMap.get(Names.AUTHORIZATION).fold(unauthorized(request)) {
      authParams => authParams.split(":") match {
        case Array(username, password) =>
          if (loginService.login(username, password)) {
          val authorizedRequest = AuthorizedRequest(request, UserData(username, List("guest")))
          continue(authorizedRequest)
        } else unauthorized(request)
        case _ => unauthorized(request)
      }
    }
  }

  def unauthorized(request: Request): Future[Response] = {
    Future.value(Response(request.version, Status.Unauthorized))
  }
}
