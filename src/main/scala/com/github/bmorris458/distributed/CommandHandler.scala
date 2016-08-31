package com.github.bmorris458.distributed

import model._
import scalaz._
import Scalaz._
import org.joda.time.DateTime


object CommandHandler {
  def main(args: Array[String]) = {

  	val parcelId = ParcelId("hello")
  	val origin = PackageOrigin("FiDi", "12345")
  	val shippingFacility = ShippingFacility("Jersey", "37373")

 	val response = ParcelOps.schedulePickup(parcelId, origin, shippingFacility, new DateTime)

 	response match {
 		case Success(_) => println("done, i'm happy!") //Put into Kafka, let client know
 		case Failure(_) => println("i paid for this package!") //Let client know
 	}

  }
}
