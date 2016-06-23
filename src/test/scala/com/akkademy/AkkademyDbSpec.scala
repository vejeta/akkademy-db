package com.akkademy

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import org.scalatest.{BeforeAndAfterEach, FunSpecLike, Matchers}

/**
  * Created by mendezr on 23/06/16.
  */
class AkkademyDbSpec extends FunSpecLike with Matchers with BeforeAndAfterEach {
  implicit val system = ActorSystem()
  describe("akkademyDb") {
    describe("given SetRequest") {
      it("should place key/value into map"){
        val actorRef = TestActorRef(new AkkademyDb)
        actorRef ! SetRequest("key", "value")
        val akkademyDb = actorRef.underlyingActor
        akkademyDb.map.get("key") should equal (Some("value"))
      }
    }
    describe("given two SetRequest"){
      it("should behave correctly") {
        val actorRef = TestActorRef(new AkkademyDb)
        actorRef ! SetRequest("key1", "value1")
        actorRef ! SetRequest("key2", "value2")
        val akkademyDb = actorRef.underlyingActor
        akkademyDb.map.get("key1") should equal (Some("value1"))
        akkademyDb.map.get("key2") should equal (Some("value2"))

      }
    }
  }

}
