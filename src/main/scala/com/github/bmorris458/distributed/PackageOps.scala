package com.github.bmorris458.distributed

import scalaz._
import Scalaz._
import org.joda.time.DateTime
import model._

object PackageOps {
  def schedulePickup(src: String, dst: String, timestamp: DateTime): ValidationNel[String,Package] = {
    (checkAddress(src) |@|
     checkAddress(dst) |@|
     Set(Record("Pickup Scheduled", timestamp)).successNel
     ) { (s, d, r) => ReadyPackage(s, d, r) }
  }

  def checkAddress(addr: String): ValidationNel[String, String] = {
    if(addr.isEmpty) "Empty address".failureNel
    else addr.successNel
  }

  def deliverPackage(pk: ActivePackage, timestamp: DateTime): ValidationNel[String, DeliveredPackage] = {
    DeliveredPackage(pk.src, pk.dst, pk.history + Record("Package Delivered", timestamp)).success
  }
}
