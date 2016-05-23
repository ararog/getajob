package br.net.bmobile.getajob.services

import br.net.bmobile.getajob.models.{Job, JobUpdate}
import br.net.bmobile.getajob.models.db.{JobsTable}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by rogerio on 3/28/16.
  */
object JobsService extends JobsService

trait JobsService extends JobsTable {

  import driver.api._

  def getJobById(id: Long): Future[Option[Job]] = db.run(jobs.filter(_.id === id).result.headOption)

  def updateJob(id: Long, jobUpdate: JobUpdate): Future[Option[Job]] = getJobById(id).flatMap {
    case Some(job) =>
      val updatedJob = jobUpdate.merge(job)
      db.run(jobs.filter(_.id === id).update(updatedJob)).map(_ => Some(updatedJob))
    case None => Future.successful(None)
  }
}