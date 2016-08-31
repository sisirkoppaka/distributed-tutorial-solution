package com.github.bmorris458.distributed

import model._
import scalaz._
import Scalaz._
import org.joda.time.DateTime


object QueryHandler {
  def main(args: Array[String]) = {


  	val parcelId = ParcelId("hello")
  	val origin = PackageOrigin("FiDi", "12345")
  	val shippingFacility = ShippingFacility("Jersey", "37373")

  	//read from Kafka, everything coming in is Valid
  	val recordExample = ScheduledPickup(parcelId, origin, shippingFacility, new DateTime)
  	var currentState = ParcelState()
  	currentState = handleRecord(recordExample, currentState)
  }

  def handleRecord(incoming: Record, state: ParcelState ): ParcelState = {
  	incoming match {
  		case ScheduledPickup(parcelId, origin, dst, ts) => state + (parcelId -> ReadyParcel(parcelId, origin, dst))
  		case _ => {
  			println("unknown operation")
  			state
  		}
  	}
  }
}
