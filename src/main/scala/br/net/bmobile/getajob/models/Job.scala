package br.net.bmobile.getajob.models

/**
  * Created by rogerio on 3/28/16.
  */
case class Job(id: Long, title: String, description: String, zipcode: String, companyId: Long)

case class JobUpdate(title: Option[String] = None,
                     description: Option[String] = None,
                     zipcode: Option[String] = None,
                     companyId: Option[Long] = None) {
  def merge(job: Job): Job = {
    Job(
      job.id,
      title.getOrElse(job.title),
      description.getOrElse(job.description),
      zipcode.getOrElse(job.zipcode),
      companyId.getOrElse(job.companyId))
  }
}