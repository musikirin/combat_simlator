package com.musikirin.bfSim

import processing.core.PApplet

/**
  * 人間を模したインスタンスです。
  * パラメータはある程度ランダムで生成され、これにより個性を作ります。
  * TODO: implicitを使って、ellipseをDoubleでも呼べるようにする
  */

case class Position(var pos_x: Double, var pos_y: Double)

class Soldier(
               val parent: PApplet,
               val id: Int, var pos_x: Double,
               var pos_y: Double,
               val platoon_id: Int,
               val team_id: Int
             ) extends Human {
  override var size_h: Double = 10
  override var size_w: Double = 5
  override var max_hp: Int = 90 + (math.random() * 20).toInt
  override var hp: Int = max_hp
  override var attack: Int = 60 + (math.random() * 40).toInt
  override var gun_skills: Int = 60 + (math.random() * 80).toInt
  override var radian: Double = math.random() * 2
  override var speed: Double = 0.5
  override var hardness: Double = 50

  override def draw(): Unit = {
    // HP0を回収するスクリプトに依存するので注意
    if (pos_x < 0 || pos_x > parent.width || pos_y < 0 || pos_y > parent.height) {
      hp = 0
    }
    // TODO:ガード分で掃除されるまえにBody1インスタンスを作りたい。
    if (hp <= 0) gabage :+= Body(parent, pos_x, pos_y, size_w)
    else {
      parent.fill(Team.color(team_id)._1, Team.color(team_id)._2, Team.color(team_id)._3)
      parent.ellipse(pos_x, pos_y, size_w, size_w)
      //      println(pos_x + " " + pos_y + " / " + hp)
    }
  }

  def fire(): Bullet = {
    //    println(s"$id is FIRE!!")
    new Bullet(
      parent,
      objectCounter(),
      pos_x + (math.cos(radian * math.Pi) * (size_w + Bullet.size_w + 2)),
      pos_y + (math.sin(radian * math.Pi) * (size_w + Bullet.size_w + 2)),
      radian
    )
  }
}

object Soldier {
  def apply(parent: PApplet, pos_x: Double, pos_y: Double, platoon_id: Int, team_id: Int): Soldier = {
    new Soldier(parent, objectCounter(), pos_x, pos_y, platoon_id, team_id)
  }
}