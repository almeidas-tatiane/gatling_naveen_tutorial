package simulations

import io.gatling.core.Predef.Simulation
import io.gatling.http.Predef._
import io.gatling.core.Predef._



class UpdateAndDeleteUserSimulation extends Simulation {
  val httpConf = http.baseUrl("http://reqres.in")
    .header(name="Accept", value="application/json")
    .header("content-type", value = "application/json")

  val scn = scenario("Update User Scenario")
    //first updating the user
    .exec(http("Update a specfic user")
      .put("/api/users/2")
      .body(RawFileBody("./src/test/resources/bodies/UpdateUser.json")).asJson
      .check(status.in(200 to 210)))

    .pause(3)

    //second - deleting the user
    .exec(http("Delete User Scenario")
      .delete("/api/users/2")
      .check(status.in(200 to 204)))

  setUp(scn.inject(atOnceUsers(1))).protocols(httpConf)

}
