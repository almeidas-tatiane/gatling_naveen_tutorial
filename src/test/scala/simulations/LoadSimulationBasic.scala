package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.core.Predef.Simulation
import scala.concurrent.duration._
import scala.language.postfixOps

class LoadSimulationBasic extends Simulation {

  val httpConf = http.proxy(Proxy("localhost",8888))
    .baseUrl("https://gorest.co.in")
    .header("Authorization", value = "Bearer ec2ee2eb3e4618e566e4ffbaaf78ea53646b3937dcd630ea6a39c1eb48235e36")

  val csvFeeder = csv("./src/test/resources/data/getUserLoad.csv").circular


  var i: String = "0"

  def getAnUser() = {
    repeat(1, i) {
      feed(csvFeeder)
        .exec(http("Get Single User Request")
          .get("/public/v1/users/${userid}")
          .check(jsonPath("$.data.name").is("${name}"))
          .check(status.in(200, 304)))
        .pause(2)
    }
  }

  val scn = scenario("Basic Load Simulation").exec(getAnUser())

  setUp(scn.inject(nothingFor(5),
    atOnceUsers(10),
    rampUsers(15) during (5 seconds)
  ).protocols(httpConf)
  )
}