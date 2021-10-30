package simulations

import io.gatling.core.Predef.Simulation
import io.gatling.http.Predef._
import io.gatling.core.Predef._
import io.gatling.core.structure._

class DataFeederCsv extends Simulation {
  //Types of csv feeder:
  //CIRCULAR: When you want to reuse data after EOF
  //SHUFFLE, RANDOM: are similar
  //QUEUE: Will use the CSV data in the same order it's and it won't reuse it

  val httpConf = http.baseUrl("https://gorest.co.in")
    .header("Authorization",value= "Bearer ec2ee2eb3e4618e566e4ffbaaf78ea53646b3937dcd630ea6a39c1eb48235e36")

  val csvFeeder = csv("./src/test/resources/data/getUser.csv").circular



  var i: String = "0"

  def getAnUser() = {
    repeat(7,i){
      feed(csvFeeder)
        .exec(http("Get Single User Request")
          .get("/public/v1/users/${userid}")
          .check(jsonPath("$.data.name").is("${name}"))
          .check(status.in(200,304)))
        .pause(2)
    }
  }

  val scn = scenario("CSV FEEDER TEST").exec(getAnUser())

  setUp(scn.inject(atOnceUsers(1))).protocols(httpConf)

}
