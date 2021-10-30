package simulations

import io.gatling.core.Predef.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._


class CheckResponseAndExtractData extends Simulation {

  val httpConf = http.baseUrl("https://gorest.co.in/")
    .header("Authorization", value = "Bearer ec2ee2eb3e4618e566e4ffbaaf78ea53646b3937dcd630ea6a39c1eb48235e36")

  val scn = scenario("Check Correlation and Extract Data")
    // First call - get all the users
    .exec(http("Get all users")
      .get("public/v1/users")
      .check(jsonPath("$.data[0].id").saveAs("userId")))

    // Second call - get a specific user on the basis of id
    .exec(http("Get Specific User")
      .get("public/v1/users/${userId}")
      .check(jsonPath("$.data.id").is("3562"))
      .check(jsonPath("$.data.name").is("Andres"))
      .check(jsonPath("$.data.email").is("Juan26069@iuvbiud.com")))

  setUp(scn.inject(atOnceUsers(1))).protocols(httpConf)

}
