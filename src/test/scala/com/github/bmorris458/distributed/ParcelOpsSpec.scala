package com.github.bmorris458.distributed

import org.joda.time.DateTime
import org.scalatest.{FlatSpec, Matchers}

import scalaz._
import Scalaz._
import model._

class ParcelOpsSpec extends FlatSpec with Matchers {
  import ParcelOps._

  val s = PackageOrigin("101 Start Pl", "10001")
  val f = ShippingFacility("999 Post Av", "10011")
  val d = PackageDestination("456 End Rd #123", "10021")
  val ts1 = new DateTime
  val rec1 = Set[Record](ScheduledPickup(ts1))
  val ts2 = new DateTime
  val rec2 = rec1 + PickedUp(ts2)
  val ts3 = new DateTime
  val rec3 = rec2 + Received(ts3, f)
  val ts4 = new DateTime
  val rec4 = rec3 + Dispatched(ts4, f, d)
  val ts5 = new DateTime
  val failMsg = "No one available to sign"
  val rec5 = rec4 + DeliveryFailed(ts5, failMsg)
  val ts6 = new DateTime
  val rec6 = rec5 + Delivered(ts6)

  "schedulePickup" should "create a ReadyParcel on valid addresses" in {
    schedulePickup(s, d, ts1) shouldBe ReadyParcel(s, d, rec1).success
  }

  "pickupPackage" should "turn a ReadyParcel into an ActiveParcel" in {
    val pkg = ReadyParcel(s, d, rec1)
    pickupPackage(pkg, ts2) shouldBe ActiveParcel(s, d, rec2).success
  }

  "receivePackage" should "add a history to an ActiveParcel" in {
    val pkg = ActiveParcel(s, d, rec2)
    receivePackage(pkg, ts3, f) shouldBe ActiveParcel(s, d, rec3).success
  }

  "dispatchPackage" should "add a history to an ActiveParcel" in {
    val pkg = ActiveParcel(s, d, rec3)
    dispatchPackage(pkg, ts4, f, d) shouldBe ActiveParcel(s, d, rec4).success
  }

  "failDeliverPackage" should "add a history to an ActiveParcel" in {
    val pkg = ActiveParcel(s, d, rec4)
    failDeliverPackage(pkg, ts5, failMsg) shouldBe ActiveParcel(s, d, rec5).success
  }

  "deliverPackage" should "turn an ActiveParcel into a DeliveredParcel" in {
    val pkg = ActiveParcel(s, d, rec5)
    deliverPackage(pkg, ts6) shouldBe DeliveredParcel(s, d, rec6).success
  }
}
