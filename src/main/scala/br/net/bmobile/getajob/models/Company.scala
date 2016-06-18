package br.net.bmobile.getajob.models

case class Company(id: Long, name: String)

case class CompanyUpdate(name: Option[String] = None) {
  def merge(company: Company): Company = {
    Company(company.id, name.getOrElse(company.name))
  }
}