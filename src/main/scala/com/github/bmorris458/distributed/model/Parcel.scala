package com.github.bmorris458.distributed.model


trait Parcel {
  val src: Location
  val dst: Location
}

case class ParcelId(_id: String)

case class ReadyParcel(pid: ParcelId, src: Location, dst: Location ) extends Parcel
case class ActiveParcel(pid: ParcelId, src: Location, dst: Location) extends Parcel
case class DeliveredParcel(pid: ParcelId, src: Location, dst: Location) extends Parcel
