package com.github.bmorris458.distributed

import org.joda.time.DateTime
import org.scalatest.{FlatSpec, Matchers}

import scalaz._
import Scalaz._
import model._

class PackageOpsSpec extends FlatSpec with Matchers {
  import PackageOps._

  val s = "101 Start Pl, New York, NY 10001"
  val d = "456 End Rd #123, Brooklyn, NY 10021"
  val ts1 = new DateTime
  val rec1 = Set(Record("Pickup Scheduled", ts1))
  val ts2 = new DateTime
  val rec2 = rec1 + Record("Package Picked Up", ts2)
  val ts3 = new DateTime
  val rec3 = rec2 + Record("Package Delivered", ts3)

  "A Package Processor" should "succeed on well-formed addresses" in {
    assert(checkAddress(s).isSuccess)
  }

  it should "fail on mal-formed addresses" in {
    assert(checkAddress("").isFailure)
  }

  "schedulePickup" should "create a ReadyPackage on valid addresses" in {
    schedulePickup(s, d, ts1) shouldBe ReadyPackage(s, d, rec1).success
  }

  "deliverPackage" should "turn an ActivePackage into a DeliveredPackage" in {
    val pkg = ActivePackage(s, d, rec2)
    deliverPackage(pkg, ts3) shouldBe DeliveredPackage(s, d, rec3).success
  }
}
