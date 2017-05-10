package com.musikirin.bfSim

import processing.core.PApplet

/**
  * Created by kirin on 2017/05/01.
  */
class Bullet(val parent: PApplet, val id: Int, var pos_x: Double, var pos_y: Double, var degree: Double) extends MovableObject {
  override var size_w: Double = Bullet.size_w
  override var speed: Double = 10
  override var max_hp: Int = 1
  override var hp: Int = max_hp
  override var attack: Int = 150
  override var hardness: Double = 100

  override def draw(): Unit = {
    // HP0を回収するスクリプトに依存するので注意
    if (pos_x < 0 || pos_x > parent.width || pos_y < 0 || pos_y > parent.height) {
      hp = 0
    }
    if (hp > 0) {
      parent.fill(255, 255, 255)
      parent.ellipse(pos_x, pos_y, size_w, size_w)
      //      println(pos_x + " " + pos_y + " / " + hp)
    }
  }
}

object Bullet {
  val size_w: Double = 3
  def apply(parent: PApplet, pos_x: Double, pos_y: Double, radian: Double): Unit = {
    new Bullet(parent, objectCounter(), pos_x, pos_y, radian)
  }
}