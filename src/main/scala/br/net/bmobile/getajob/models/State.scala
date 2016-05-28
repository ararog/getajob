package br.net.bmobile.getajob.models

/**
  * Created by rogerio on 3/28/16.
  */
case class State(id: Option[Long] = None, name: String)

case class StateUpdate(name: Option[String] = None) {
  def merge(state: State): State = {
    State(state.id, name.getOrElse(state.name))
  }
}