//package simulations
//
//import io.gatling.core.Predef.Simulation
//import io.gatling.core.Predef._
//import io.gatling.core.structure._
//import io.gatling.http.Predef._
//
//class LoopRequest extends Simulation {
//
//  val httpConf = http.baseUrl("http://reqres.in")
//    .header("Accept", value = "application/json")
//    .header("content-type", value="application/json")
//
//
//  def getAllUsersRequest() = {
//    repeat(2){
//        .exec(http("Get All Users Request")
//        .get("/api/users?page=2")
//        .check(status.is(200)))
//    }
//  }
//
//
//  def getAnUsersRequest() ={
////    repeat(2) {
//      .exec(http("Get An User Request")
//        .get("/api/users/2")
//        .check(status.is(200)))
////    }
//  }
//
//  def addAnUser() = {
////    repeat(2){
//      .exec(http("Add An User Request")
//        .post("/api/users")
//        .body(RawFileBody("src/test/resources/bodies/AddUser.json")).asJson
//        .check(status.is(201)))
////    }
//  }
//
//  val scn = scenario("User Request Scenario")
//    .exec(getAllUsersRequest())
//    .pause(2)
//    .exec(getAnUsersRequest())
//    .pause(2)
//    .exec(addAnUser())
//
//  setUp(scn.inject((atOnceUsers(1)))).protocols(httpConf)
//
//
//
//
//}
