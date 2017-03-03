import com.twitter.finagle.http.{Request, RequestProxy}
import data.UserData

case class AuthorizedRequest(request: Request, userData: UserData) extends RequestProxy
