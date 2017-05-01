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
class Body(val parent: PApplet, val id: Int, var pos_x: Double, var pos_y: Double, var width: Double) extends FieldObject {
  override var max_hp: Int = 100
  override var hp: Int = max_hp
  override var hardness: Double = 50

  /**
    * HPが1以上の時はHPが減り続けます。
    */
  override def draw(): Unit = {
    // TODO: HP0を回収するスクリプトに依存するので注意
    if (hp >= 0) {
      hp -= 1
      parent.fill(100, 100, 100)
      parent.ellipse(pos_x, pos_y, width, width)
      //      println(pos_x + " " + pos_y + " / " + hp)
    }
  }

}

object Body {
  def apply(parent: PApplet, pos_x: Double, pos_y: Double, width: Double): Body = {
    new Body(parent, objectCounter(), pos_x, pos_y, width)
  }
}
