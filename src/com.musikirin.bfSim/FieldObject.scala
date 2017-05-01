package com.musikirin.bfSim

/**
  * 画面に描画するすべてのオブジェクトです。
  * すべてのオブジェクトはHPを持ち、HPがなくなればインスタンスが削除されます。
  */
trait FieldObject {
  val id: Int
  var pos_x: Double
  var pos_y: Double
  var max_hp: Int
  var hp: Int
  var hardness: Double

  def draw(): Unit
  def damage(point: Int): Unit = hp -= point
}

/**
  * オブジェクトの中でも、動く可能性があるオブジェクトです。
  */
trait MovableObject extends FieldObject {
  var size_w: Double
  var radian: Double
  var speed: Double
  var attack: Int

  def move(amount: Double): Unit = {
    pos_x += speed * amount * math.cos(radian * math.Pi)
    pos_y += speed * amount * math.sin(radian * math.Pi)
  }
}

/**
  * 動くオブジェクトの中で、人間として扱うオブジェクトです。
  */
trait Human extends MovableObject {
  var size_h: Double
  var gun_skills: Int
}