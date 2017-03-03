trait LoginService {
  def login(username: String, password: String): Boolean
}

class AlwaysSuccessfulLoginService extends LoginService {
  override def login(username: String, password: String): Boolean = true
}


