package br.net.bmobile.getajob.models

/**
  * Created by rogerio on 3/28/16.
  */
case class Candidate(id: Option[Long] = None, name: String)

case class CandidateUpdate(name: Option[String] = None) {
  def merge(candidate: Candidate): Candidate = {
    Candidate(candidate.id, name.getOrElse(candidate.name))
  }
}