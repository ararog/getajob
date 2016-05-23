package br.net.bmobile.getajob.services

import br.net.bmobile.getajob.models.{Candidate, CandidateUpdate}
import br.net.bmobile.getajob.models.db.{CandidatesTable}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by rogerio on 3/28/16.
  */
object CandidatesService extends CandidatesService

trait CandidatesService extends CandidatesTable {

  import driver.api._

  def getCandidateById(id: Long): Future[Option[Candidate]] = db.run(candidates.filter(_.id === id).result.headOption)

  def updateCandidate(id: Long, candidateUpdate: CandidateUpdate): Future[Option[Candidate]] = getCandidateById(id).flatMap {
    case Some(candidate) =>
      val updatedCandidate = candidateUpdate.merge(candidate)
      db.run(candidates.filter(_.id === id).update(updatedCandidate)).map(_ => Some(updatedCandidate))
    case None => Future.successful(None)
  }
}