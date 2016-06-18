package br.net.bmobile.getajob.models

/**
  * Created by rogerio on 3/28/16.
  */
case class City(id: Long, name: String, stateId: Long)

case class CityUpdate(name: Option[String] = None, stateId: Option[Long] = None) {
  def merge(city: City): City = {
    City(city.id, name.getOrElse(city.name), stateId.getOrElse(city.stateId))
  }
}