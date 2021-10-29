package simulations

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class TestAPISimulation extends Simulation {

  //http configuration
  val httpConf = http.baseUrl("https://reqres.in")
      .header(name="Accept", value="application/json")
      .header(name="content-type", value="application/json")

  //scenario
  val scn = scenario(scenarioName="get user")
      .exec(http(requestName = "get user request")
        .get("/api/users?page=2")
        .check(status is 200))

  //setup
    setUp(scn.inject(atOnceUsers(1000))).protocols(httpConf)

}
