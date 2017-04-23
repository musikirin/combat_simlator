package com.musikirin.bfSim

/**
  * Created by ninak on 2017/04/24.
  */
trait Human extends FieldObject {
  val human_id:Int
  val height:Float
  val width:Float
  val max_hp:Int
  val brunt_skills:Int
  val gun_skills:Int
  val radian:Float
  val speed:Float
}

trait Affiliation extends Human {
  val team_id:Int
  val rank:Int
}

trait Group extends FieldObject {
  val group_id:Int
  val radian:Float
  val speed:Float
  val relative_x:Float
  val relative_y:Float
}