package com.akkademy

import akka.actor.Actor
import akka.event.Logging

import scala.collection.mutable

/**
  * Created by mendezr on 23/06/16.
  */

case class SetRequest(key: String, value: Object)

class AkkademyDb extends Actor {
  val map = new mutable.HashMap[String, Object]
  val log = Logging(context.system, this)

  override def receive = {
    case SetRequest(key, value) => {
      log.info("received SetRequest - key: {} value: {}", key, value)
      map.put(key, value)
    }
    case o => log.info("received unknown message: {}", o)
  }

}
