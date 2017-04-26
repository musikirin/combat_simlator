package com.musikirin.bfSim

/**
  * Created by ninak on 2017/04/24.
  */
trait Affiliation {
  val team_id: Int
  var rank: Int
  var member: Vector[Group]
}

trait Group {
  val group_id: Int
  var radian: Float
  var speed: Float
  var member: Vector[Human]
}