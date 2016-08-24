package com.github.bmorris458.distributed

import org.joda.time.DateTime

import scalaz._
import Scalaz._
import model._

object ParcelOps {
  def schedulePickup(src: Location, dst: Location, ts: DateTime): ValidationNel[String,Parcel] = {
    ReadyParcel(src, dst, Set[Record](ScheduledPickup(ts))).success
  }

  def pickupPackage(pk: ReadyParcel, ts: DateTime): ValidationNel[String, ActiveParcel] = {
    ActiveParcel(pk.src, pk.dst, pk.history + PickedUp(ts)).success
  }

  def receivePackage(pk: ActiveParcel, ts: DateTime, loc: Location): ActiveParcel = {
    ActiveParcel(pk.src, pk.dst, pk.history + Received(ts, loc))
  }

  def dispatchPackage(pk: ActiveParcel, ts: DateTime, loc: Location, dst: Location): ActiveParcel = {
    ActiveParcel(pk.src, pk.dst, pk.history + Dispatched(ts, loc, dst))
  }

  def deliverPackage(pk: ActiveParcel, ts: DateTime): ValidationNel[String, DeliveredParcel] = {
    DeliveredParcel(pk.src, pk.dst, pk.history + Delivered(ts)).success
  }
}
