package br.net.bmobile.getajob.services

import br.net.bmobile.getajob.models.{Company, CompanyUpdate}
import br.net.bmobile.getajob.models.db.CompaniesTable

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object CompaniesService extends CompaniesService

trait CompaniesService extends CompaniesTable {

  import driver.api._

  def getCompanies(): Future[Seq[Company]] = db.run(companies.result)

  def getCompanyById(id: Long): Future[Option[Company]] = db.run(companies.filter(_.id === id).result.headOption)

  def createCompany(company: Company): Future[Company] = db.run(companies returning companies += company)

  def updateCompany(id: Long, companyUpdate: CompanyUpdate): Future[Option[Company]] = getCompanyById(id).flatMap {
    case Some(user) =>
      val updatedCompany = companyUpdate.merge(user)
      db.run(companies.filter(_.id === id).update(updatedCompany)).map(_ => Some(updatedCompany))
    case None => Future.successful(None)
  }

  def deleteCompany(id: Long): Future[Int] = db.run(companies.filter(_.id === id).delete)

}