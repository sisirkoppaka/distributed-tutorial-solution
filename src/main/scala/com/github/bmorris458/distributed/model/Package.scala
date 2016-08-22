package com.github.bmorris458.distributed.model

trait Package {
  val src: String
  val dst: String
}

case class ReadyPackage(src: String, dst: String, history: Set[Record]) extends Package
case class ActivePackage(src: String, dst: String, history: Set[Record]) extends Package
case class DeliveredPackage(src: String, dst: String, history: Set[Record]) extends Package
