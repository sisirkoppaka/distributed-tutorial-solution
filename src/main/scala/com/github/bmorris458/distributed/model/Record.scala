package com.github.bmorris458.distributed.model

import org.joda.time.DateTime

trait Record {
  val ts: DateTime
}
case class ScheduledPickup(ts: DateTime) extends Record
case class PickedUp(ts: DateTime) extends Record
case class PickupFailed(ts: DateTime, reason: String) extends Record
case class Received(ts: DateTime, location: Location) extends Record
case class Dispatched(ts: DateTime, location: Location, destination: Location) extends Record
case class Delivered(ts: DateTime) extends Record
case class DeliveryFailed(ts: DateTime, reason: String) extends Record
