package processors

import java.util.concurrent.TimeUnit

import com.twitter.util.{Duration, Future, Timer}

trait ReportProcessor {
  def processReport(id: Int): Future[Unit]

}
class FakeReportProcessor extends ReportProcessor {

  implicit val timer = Timer.Nil

  override def processReport(id: Int): Future[Unit] = {
    Future.sleep(Duration(2, TimeUnit.SECONDS))
  }
}
