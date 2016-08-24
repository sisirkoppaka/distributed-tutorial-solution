package com.github.bmorris458.distributed.model

trait Parcel {
  val src: Location
  val dst: Location
}

case class ReadyParcel(src: Location, dst: Location, history: PkgHistory) extends Parcel
case class ActiveParcel(src: Location, dst: Location, history: PkgHistory) extends Parcel
case class DeliveredParcel(src: Location, dst: Location, history: PkgHistory) extends Parcel
