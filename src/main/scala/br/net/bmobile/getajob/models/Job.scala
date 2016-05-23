package br.net.bmobile.getajob.models

/**
  * Created by rogerio on 3/28/16.
  */
case class Job(id: Option[Long] = None, title: String, description: String)

case class JobUpdate(title: Option[String] = None, description: Option[String] = None) {
  def merge(job: Job): Job = {
    Job(job.id, title.getOrElse(job.title), description.getOrElse(job.description))
  }
}