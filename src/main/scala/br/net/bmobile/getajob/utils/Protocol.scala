package br.net.bmobile.getajob.utils

import br.net.bmobile.getajob.models._
import spray.json.DefaultJsonProtocol

trait Protocol extends DefaultJsonProtocol {
  implicit val usersFormat = jsonFormat3(User)
  implicit val candidatesFormat = jsonFormat2(Candidate)
  implicit val companiesFormat = jsonFormat2(Company)
  implicit val jobsFormat = jsonFormat5(Job)
  implicit val countriesFormat = jsonFormat2(Country)
  implicit val statesFormat = jsonFormat3(State)
  implicit val citiesFormat = jsonFormat3(City)
}
