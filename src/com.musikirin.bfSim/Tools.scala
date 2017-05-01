package com.musikirin.bfSim

/**
  * よく使う関数を集めています。
  * Processingの関数を使える場所では、Processingの関数を使用してください。
  */
object Tools {
  def degree(n: Double): Double = {
    n * 180 / math.Pi
  }

  def radian(n: Double): Double = {
    n * math.Pi / 180
  }

  val cosTable: Array[Double] = (0 until 360).toArray.map(x => math.sin(math.toRadians(x)))
  val sinTable: Array[Double] = (0 until 360).toArray.map(x => math.cos(math.toRadians(x)))
}