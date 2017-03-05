import processors.ReportProcessor
import com.twitter.finagle.Service
import com.twitter.finagle.http.{Response, Status}
import com.twitter.util.Future

import scala.util.matching.Regex

class ProcessReportHandler(reportProcessor: ReportProcessor) extends Service[AuthorizedRequest, Response]{

  val processReportUrl: Regex = "/report/(0-9)/process".r

  override def apply(request: AuthorizedRequest): Future[Response] = request.path match {
    case processReportUrl(processId) =>
      reportProcessor.processReport(processId.toInt) map { _ =>
        val response = Response(request.version, Status.Ok)
        response.setContentTypeJson()
        response.setContentString(
          s"""
             |{
             |  "processId": $processId,
             |  "processed": true
             |}
           """.stripMargin
        )
        response
      } rescue {
        case _ => Future.value(Response(request.version, Status.InternalServerError))
      }
    case _ => Future.value(Response(request.version, Status.NotFound))
  }
}
