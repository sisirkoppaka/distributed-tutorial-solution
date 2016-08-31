package com.github.bmorris458.distributed.model

import org.joda.time.DateTime

trait Record {
  val ts: DateTime
  val pid: ParcelId
}
case class ScheduledPickup(pid: ParcelId, src: Location, dst: Location, ts: DateTime) extends Record
case class PickedUp(pid: ParcelId, ts: DateTime) extends Record
case class PickupFailed(ts: DateTime, pid: ParcelId, reason: String) extends Record
case class Received( pid: ParcelId, location: ShippingFacility, ts: DateTime) extends Record
case class Dispatched(pid: ParcelId, src: ShippingFacility, destination: Location, ts: DateTime) extends Record
case class Delivered(pid: ParcelId, ts: DateTime) extends Record
case class DeliveryFailed(pid: ParcelId, reason: String, ts: DateTime) extends Record
