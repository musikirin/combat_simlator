package com.musikirin.bfSim

import processing.core.PApplet

/**
  * 死体を定義しています。自動的にHPが減り続けます。
  *
  * @param parent PApplet
  * @param id     死体ID
  * @param pos_x  座標X
  * @param pos_y  座標Y
  * @param width  大きさ（死体は高さを取らない）
  */
class Body(val parent: PApplet, val id: Int, var pos_x: Float, var pos_y: Float, var width: Float) extends FieldObject {
  override var max_hp: Int = 100
  override var hp: Int = max_hp
  override var hardness: Float = 50

  /**
    * HPが1以上の時はHPが減り続けます。
    */
  override def draw(): Unit = {
    // TODO: HP0を回収するスクリプトに依存するので注意
    if (hp >= 0) {
      hp -= 1
      parent.fill(255, 255, 255)
      parent.ellipse(pos_x, pos_y, width, width)
    }
  }
}

object Body {
  private var count = 0

  def apply(parent: PApplet, pos_x: Float, pos_y: Float, width: Float): Body = {
    new Body(parent, counter(), pos_x, pos_y, width)
  }

  def counter(): Int = {
    count += 1
    count - 1
  }
}