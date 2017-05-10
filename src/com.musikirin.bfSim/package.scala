package com.musikirin

import processing.core.PApplet

/**
  * パッケージ共通で利用するデータや関数がまとまっています。
  * implicit classなどの表記もここへ記述してください。
  */
package object bfSim {

  /*設定*/
  // 銃の精度 高いほど命中率が上がる
  var guns_accuracy = 36

  /* グローバルデータ */
  var objctCounter: Int = 0
  var obj: Vector[MovableObject] = Vector()
  var soldiers_list: Vector[Soldier] = Vector()
  var bullets_list: Vector[Bullet] = Vector()
  var gabage: Vector[FieldObject] = Vector()

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

  def objectCounter(): Int = {
    val n = objctCounter
    objctCounter += 1
    n
  }

  /**
    * ラジアン同士の角度の差を計算する
    *
    * @param a 入力1
    * @param b 入力2
    * @return 差を調べるため、最大は1となる
    */
  def defferenceBetweenRadian(a: Double, b: Double): Double = {
    val c = math.abs(a - b)
    if (c > 1) 2 - c
    else c
  }


  /**
    * 円形の図形同士が接触しているか判定します。
    * 平方根（高さ^2 + 横^2) - 両オブジェクトの半径
    *
    * @param a
    * @param b
    * @return
    */
  def ellipseDistance(a: MovableObject, b: MovableObject): Double = {
    math.sqrt(math.pow(math.abs(a.pos_x - b.pos_x), 2) + math.pow(math.abs(a.pos_y - b.pos_y), 2)) - ((a.size_w + b.size_w) / 2)
  }
}
