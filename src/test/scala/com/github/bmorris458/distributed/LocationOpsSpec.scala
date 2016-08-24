package com.github.bmorris458.distributed

import org.scalatest.{FlatSpec, Matchers}
import LocationOps._

class LocationOpsSpec extends FlatSpec with Matchers {
  behavior of "checkZip"

  val goodAddress = "111 This Place"
  val badAddress = ""
  val goodZipBasic = "10001"
  val goodZipLong = goodZipBasic + "-1234"
  val badZips = Set("", "1", "1-1", "1a2b3", "1234567890", "12345-123a", "123456789011", "a-12345678")
  it should "accept a well-formed basic (5 digit) zip" in {
    checkZip(goodZipBasic).isSuccess
  }

  it should "accept a well-formed 5+4 zip" in {
    checkZip(goodZipLong).isSuccess
  }

  it should "reject all mal-formed zips" in {
    assert(false)
  }
}
