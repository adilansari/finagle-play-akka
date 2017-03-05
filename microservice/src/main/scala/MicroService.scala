import java.net.InetSocketAddress

import com.softwaremill.macwire.wire
import com.twitter.finagle.builder.ServerBuilder
import com.twitter.finagle.http.Http
import processors.FakeReportProcessor

object MicroService extends App {
//  lazy val loginService = wire[AlwaysSuccessfulLoginService]
//  lazy val reportProcessor = wire[FakeReportProcessor]
  val loginService = new AlwaysSuccessfulLoginService()
  val reportProcessor = new FakeReportProcessor()
  val authenticationFilter = new AuthenticationFilter(loginService)
  val processReportHandler = new ProcessReportHandler(reportProcessor)
//  lazy val authenticationFilter: AuthenticationFilter = wire[AuthenticationFilter]
//  lazy val processReportHandler: ProcessReportHandler = wire[ProcessReportHandler]

  val serviceChain = authenticationFilter.andThen(processReportHandler)

  val socketAddress = new InetSocketAddress(9000)
  val server = ServerBuilder()
    .codec(Http())
    .bindTo(socketAddress)
    .name("HTTP endpoint")
    .build(serviceChain)

  println("microservice started")
}
