package com.github.bmorris458.distributed.model

trait Location {
  val address: String
  val zip: String
}

case class ShippingFacility(address: String, zip: String) extends Location
case class PackageOrigin(address: String, zip: String) extends Location
case class PackageDestination(address: String, zip: String) extends Location
