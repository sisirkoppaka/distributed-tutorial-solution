package com.github.bmorris458.distributed

import org.joda.time.DateTime
import org.scalatest.{FlatSpec, Matchers}

import scalaz._
import Scalaz._
import model._

class ParcelOpsSpec extends FlatSpec with Matchers {
  import ParcelOps._

  val s = PackageOrigin("101 Start Pl", "10001")
  val d = PackageDestination("456 End Rd #123", "10021")
  val ts1 = new DateTime
  val rec1 = Set[Record](ScheduledPickup(ts1))
  val ts2 = new DateTime
  val rec2 = rec1 + PickedUp(ts2)
  val ts3 = new DateTime
  val rec3 = rec2 + Delivered(ts3)

  "schedulePickup" should "create a ReadyParcel on valid addresses" in {
    schedulePickup(s, d, ts1) shouldBe ReadyParcel(s, d, rec1).success
  }

  "pickupPackage" should "turn a ReadyParcel into an ActiveParcel" in {
    val pkg = ReadyParcel(s, d, rec1)
    pickupPackage(pkg, ts2) shouldBe ActiveParcel(s, d, rec2).success
  }

  "deliverPackage" should "turn an ActiveParcel into a DeliveredParcel" in {
    val pkg = ActiveParcel(s, d, rec2)
    deliverPackage(pkg, ts3) shouldBe DeliveredParcel(s, d, rec3).success
  }
}
