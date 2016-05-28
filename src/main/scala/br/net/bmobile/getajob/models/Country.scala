package br.net.bmobile.getajob.models

/**
  * Created by rogerio on 3/28/16.
  */
case class Country(id: Option[Long] = None, name: String)

case class CountryUpdate(name: Option[String] = None) {
  def merge(country: Country): Country = {
    Country(country.id, name.getOrElse(country.name))
  }
}