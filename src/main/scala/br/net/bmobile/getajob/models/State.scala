package br.net.bmobile.getajob.models

/**
  * Created by rogerio on 3/28/16.
  */
case class State(id: Long, name: String, countryId: Long)

case class StateUpdate(name: Option[String] = None, countryId: Option[Long]) {
  def merge(state: State): State = {
    State(state.id, name.getOrElse(state.name), countryId.getOrElse(state.countryId))
  }
}