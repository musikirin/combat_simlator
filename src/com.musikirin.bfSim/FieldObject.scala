package com.musikirin.bfSim

/**
  * Created by ninak on 2017/04/24.
  */
trait FieldObject {
  val id:Int
  var pos_x: Float
  var pos_y: Float
  var max_hp: Int
  var hp: Int
  var hardness: Float

  def draw(): Unit

  def damage(point: Int): Unit = hp -= point
}

trait Human extends FieldObject {
  var height: Float
  var width: Float
  var max_hp: Int
  var brunt_skills: Int
  var gun_skills: Int
  var radian: Float
  var speed: Float
}