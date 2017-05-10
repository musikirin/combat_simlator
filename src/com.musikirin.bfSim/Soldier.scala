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
  override var gun_skills: Double = 0.5 + (math.random() / 2)
  override var degree: Double = math.random() * 360
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
    }
  }

  /**
    * 最も近い的の方向を向く
    */
  def rotate(): Unit = {
    // 違うIDで、もっとも距離が近い敵を束縛
    val list = soldiers_list.filter(_.team_id != team_id)
    if (list.nonEmpty) {
      // FIXME: nullのときにminByがエラーとなるためifを分けています。マージしたい。
      val nearest_enemy = Option(list.minBy(ellipseDistance(this, _)))
      if (nearest_enemy.nonEmpty) {
        val deg = math.atan2(nearest_enemy.get.pos_y - pos_y, nearest_enemy.get.pos_x - pos_x).toDegrees
        println(deg)
        degree = deg
      }
    }
  }

  def fire(): Bullet = {
    val ac = math.random() / guns_accuracy / gun_skills
    new Bullet(
      parent,
      objectCounter(),
      pos_x + (math.cos(degree.toRadians * math.Pi) * (size_w + Bullet.size_w + 2)),
      pos_y + (math.sin(degree.toRadians * math.Pi) * (size_w + Bullet.size_w + 2)),
      degree + (ac - ac / 2)
    )
  }

  def collusion(): Unit = {
    // 兵士と弾丸の接触判定
    for (b <- bullets_list) {
      if (id != b.id && ellipseDistance(this, b) < 0) {
        println(id + " was Hit!!!")
        hp -= b.attack
        b.hp -= attack
      }
    }
    // 兵士同士の接触判定
    for (b <- soldiers_list) {
      if (team_id != b.team_id && ellipseDistance(this, b) < 0) {
        println(id + " Attack " + b.id + " !!!")
        b.hp = b.hp - math.abs(attack / (1 + defferenceBetweenRadian(degree.toRadians, b.degree.toRadians))).toInt
      }
    }
  }
}

object Soldier {
  def apply(parent: PApplet, pos_x: Double, pos_y: Double, platoon_id: Int, team_id: Int): Soldier = {
    new Soldier(parent, objectCounter(), pos_x, pos_y, platoon_id, team_id)
  }
}