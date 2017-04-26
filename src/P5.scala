import com.musikirin.bfSim.{Soldier, _}
import processing.core.PApplet._
import processing.core._

object P5 {
  def main(args: Array[String]) {
    PApplet.main(Array[String]("P5"))
  }
}

class P5 extends PApplet {

  override def settings() = {
    fullScreen("processing.javafx.PGraphicsFX2D")
  }

  var obj = Vector[FieldObject]()

  override def setup() = {
    background(0)
    smooth()
  }

  override def keyPressed() {
    if (key == '1') {
      obj :+= Soldier(this, (math.random() * width).toFloat, (math.random() * height).toFloat)
    }
  }

  override def draw() = {
    Display.refresh(0)
    obj.foreach(_.draw())
    obj.foreach(_.damage(1))
    obj = obj.filter(_.hp > 0)
    println(obj)
  }

  object Display {
    // 画面全体を塗りつぶします。
    def refresh(gray: Int): Unit = {
      fill(gray)
      ellipse(0, 0, width, height)
    }

    def refresh(r: Int, g: Int, b: Int): Unit = {
      fill(r, g, b)
      ellipse(0, 0, width, height)
    }

    def refresh(r: Int, g: Int, b: Int, a: Int): Unit = {
      fill(r, g, b, a)
      ellipse(0, 0, width, height)
    }
  }

}