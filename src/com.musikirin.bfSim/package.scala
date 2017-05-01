package com.musikirin

import processing.core.PApplet

/**
  * パッケージ共通で利用するデータや関数がまとまっています。
  * implicit classなどの表記もここへ記述してください。
  */
package object bfSim {

  /* グローバルデータ */

  var obj: Vector[FieldObject] = Vector[FieldObject]()

  /* 計算省略用の角度変換テーブル */
  val cosTable: Array[Double] = (0 until 360).toArray.map(x => math.sin(math.toRadians(x)))
  val sinTable: Array[Double] = (0 until 360).toArray.map(x => math.cos(math.toRadians(x)))

  /* Implicit Class */

  implicit class EllipseSuportsDouble(val src: PApplet) {
    def ellipse(a: Double, b: Double, c: Double, d: Double) = {
      src.ellipse(a.toFloat, b.toFloat, c.toFloat, d.toFloat)
    }
  }

  /* 汎用関数 */

  def degree(n: Double): Double = {
    n * 180 / math.Pi
  }

  def radian(n: Double): Double = {
    n * math.Pi / 180
  }


  /**
    * 円形の図形同士が接触しているか判定します。
    *
    * @param a
    * @param b
    * @return
    */
  def ellipseDistance(a: MovableObject, b: MovableObject): Double = {
    math.sqrt(math.pow(math.abs(a.pos_x - b.pos_x), 2) + math.pow(math.abs(a.pos_y - b.pos_y), 2)) - (a.size_w + b.size_w)
  }

  def collusion(): Unit = {
    var sol = Vector[Soldier]()
    var bul = Vector[Bullet]()
    for (x <- obj) {
      x match {
        case x: Soldier => sol :+= x
        case x: Bullet => bul :+= x
      }
    }
    for {s <- sol
         b <- bul} {
      if (ellipseDistance(s, b) <= 0) {
        println(s.id + " is Hit!!!")
        s.hp -= b.attack
        b.hp -= s.attack
      }
    }
  }
}
