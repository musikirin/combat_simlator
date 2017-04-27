package com.musikirin.bfSim

/**
  * 対するチームを定義します
  */
trait Affiliation {
  val team_id: Int
  var rank: Int
  var member: Vector[Group]
}

/**
  * 同じチーム内のグループを定義します。
  */
trait Group {
  val group_id: Int
  var radian: Double
  var speed: Double
  var member: Vector[Human]
}