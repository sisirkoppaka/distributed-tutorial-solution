package com.github.bmorris458.distributed

import scala.util.Try
import scalaz._
import Scalaz._

object LocationOps {
  def checkAddress(addr: String): ValidationNel[String, String] = {
    /** Currently only checks whether the address is empty. More sophisticated methods
      * may be relevant in the future, including checking against a database of valid
      * addresses, but this may require a refactor to maintain pure functionality.
      */
    if(addr.isEmpty) "Empty address".failureNel
    else addr.successNel
  }

  def checkZip(zip: String): ValidationNel[String, String] = {
    /** Accepts two formats: 5 numbers, or 5 numbers dash 4 numbers
      * This could probably be accomplished much more cleanly with regex, but
      * this is sufficient for a first pass.
      */
    if (zip.length === 5) {
      if (Try(zip.toInt).isSuccess) zip.successNel
      else s"Invalid zip: $zip".failureNel
    }
    else if (zip.length === 10) {
      val splitZip = zip.split("-")
      if (splitZip.size != 2) s"Invalid zip: $zip".failureNel
      else if (Try(splitZip(0).toInt).isSuccess && Try(splitZip(1).toInt).isSuccess) zip.successNel
      else s"Invalid zip: $zip".failureNel
    } else s"Invalid zip: $zip".failureNel
  }
}
