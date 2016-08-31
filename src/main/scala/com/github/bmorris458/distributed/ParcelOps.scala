package com.github.bmorris458.distributed

import org.joda.time.DateTime

import scalaz._
import Scalaz._
import model._

object ParcelOps {
  //Evaluate if ts > now as a test in the beginning.
  def schedulePickup(pid: ParcelId, src: Location, dst: Location, ts: DateTime): ValidationNel[String,ScheduledPickup] = {
    ScheduledPickup(pid, src, dst, ts).success
  }

  def pickupPackage(pid: ParcelId, ts: DateTime): ValidationNel[String, PickedUp] = {
    PickedUp(pid, ts).success
  }

  def receivePackage(pid: ParcelId, loc: ShippingFacility, ts: DateTime): ValidationNel[String, Received] = {
    Received(pid, loc, ts).success
  }

  def dispatchPackage(pid: ParcelId,  src: ShippingFacility, dst: Location, ts: DateTime): ValidationNel[String, Dispatched] = {
    Dispatched( pid, src, dst, ts).success
  }

  def failDeliverPackage(pid: ParcelId, ts: DateTime, msg: String): ValidationNel[String, DeliveryFailed] = {
    DeliveryFailed( pid, msg, ts).success
  }

  def deliverPackage(pid: ParcelId, ts: DateTime): ValidationNel[String, Delivered] = {
    Delivered(pid, ts).success
  }
}
