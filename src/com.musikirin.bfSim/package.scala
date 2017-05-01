package com.musikirin
import processing.core.PApplet

/**
  * Created by kirin on 2017/04/29.
  */
package object bfSim {

  implicit class EllipseSuportsDouble(val src: PApplet) {
    def ellipse(a: Double, b: Double, c: Double, d: Double) = {
      src.ellipse(a.toFloat, b.toFloat, c.toFloat, d.toFloat)
    }
  }

  var obj: Vector[FieldObject] = Vector[FieldObject]()
}
