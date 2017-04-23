package com.musikirin.bfSim

/**
  * Created by ninak on 2017/04/24.
  */
trait FieldObject {
  val id:Int
  val pos_x:Float
  val pos_y:Float
  val hp:Int
  val hardness:Float

  def kill(): FieldObject
}
