package br.net.bmobile.getajob.models

/**
  * Created by rogerio on 3/28/16.
  */
case class City(id: Option[Long] = None, name: String)

case class CityUpdate(name: Option[String] = None) {
  def merge(city: City): City = {
    City(city.id, name.getOrElse(city.name))
  }
}