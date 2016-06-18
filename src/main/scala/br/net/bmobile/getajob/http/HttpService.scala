package br.net.bmobile.getajob.http

import akka.http.scaladsl.server.Directives._
import br.net.bmobile.getajob.http.routes._
import br.net.bmobile.getajob.utils.CorsSupport

trait HttpService extends JobsServiceRoute with CountriesServiceRoute with CandidatesServiceRoute with CompaniesServiceRoute with AuthServiceRoute with CorsSupport {

  val routes =
    pathPrefix("v1") {
      corsHandler {
        jobsRoute ~
        countriesRoute ~
        candidatesRoute ~
        companiesRoute ~
          authRoute
      }
    }

}
