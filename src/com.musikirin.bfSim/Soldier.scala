package com.musikirin.bfSim

import processing.core.PApplet

/**
  * Created by ninak on 2017/04/25.
  */
class Soldier(val parent: PApplet, val id: Int, var pos_x: Float, var pos_y: Float) extends Human {
  override var height: Float = 50
  override var width: Float = 10
  override var max_hp: Int = 90 + (math.random() * 20).toInt
  override var hp: Int = max_hp
  override var brunt_skills: Int = 60 + (math.random() * 80).toInt
  override var gun_skills: Int = 60 + (math.random() * 80).toInt
  override var radian: Float = (math.random() * 2).toFloat
  override var speed: Float = 1
  override var hardness: Float = 50

  override def draw(): Unit = {
    // TODO: HP0を回収するスクリプトに依存するので注意
    if (hp <= 0) Body(parent, pos_x, pos_y, width)
    else {
      parent.fill(255, 0, 0)
      parent.ellipse(pos_x, pos_y, width, width)
      println(pos_x + " " + pos_y)
    }
  }
}

object Soldier {
  var count = 0

  def apply(parent: PApplet, pos_x: Float, pos_y: Float): Soldier = {
    new Soldier(parent, counter(), pos_x, pos_y)
  }

  def counter(): Int = {
    count += 1
    count - 1
  }
}