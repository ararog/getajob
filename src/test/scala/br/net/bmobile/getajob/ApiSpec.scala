package br.net.bmobile.getajob

import akka.http.scaladsl.testkit.ScalatestRouteTest
import br.net.bmobile.getajob.utils.Migration
import org.scalatest.{Matchers, WordSpec}

/**
  * Created by rogerio on 18/06/2016.
  */
class ApiSpec extends WordSpec with Migration with Matchers with ScalatestRouteTest {

  override def beforeAll() {
    reloadSchema()
  }

}
