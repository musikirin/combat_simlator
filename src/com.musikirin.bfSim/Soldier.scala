package com.musikirin.bfSim

import processing.core.PApplet
import processing.core.PApplet.println

/**
  * 人間を模したインスタンスです。
  * パラメータはある程度ランダムで生成され、これにより個性を作ります。
  * TODO: implicitを使って、ellipseをDoubleでも呼べるようにする
  */
class Soldier(val parent: PApplet, val id: Int, var pos_x: Double, var pos_y: Double) extends Human {
  override var size_h: Double = 50
  override var size_w: Double = 10
  override var max_hp: Int = 90 + (math.random() * 20).toInt
  override var hp: Int = max_hp
  override var brunt_skills: Int = 60 + (math.random() * 80).toInt
  override var gun_skills: Int = 60 + (math.random() * 80).toInt
  override var radian: Double = (math.random() * 2)
  override var speed: Double = 1
  override var hardness: Double = 50

  override def draw(): Unit = {
    // HP0を回収するスクリプトに依存するので注意
    if (hp <= 0) Body(parent, pos_x, pos_y, size_w)
    else {
      parent.fill(255, 0, 0)
      parent.ellipse(pos_x, pos_y, size_w, size_w)
      println(pos_x + " " + pos_y + " / " + hp)
    }
  }

  override def move(amount: Double): Unit = {
    pos_x += speed * amount * math.cos(radian * math.Pi)
    pos_y += speed * amount * math.sin(radian * math.Pi)
  }
}

object Soldier {
  var count = 0

  def apply(parent: PApplet, pos_x: Double, pos_y: Double): Soldier = {
    new Soldier(parent, counter(), pos_x, pos_y)
  }

  def counter(): Int = {
    count += 1
    count - 1
  }
}