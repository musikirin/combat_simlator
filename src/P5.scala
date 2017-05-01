import com.musikirin.bfSim._
import processing.core.PApplet._
import processing.core._

object P5 {
  def main(args: Array[String]) {
    PApplet.main(Array[String]("P5"))
  }
}

/**
  * Processingユーザが扱うエリアです。
  */
class P5 extends PApplet {

  override def settings() = {
    // 描画を軽量化するためにjavafx以外使用しないでください。
    //    fullScreen("processing.javafx.PGraphicsFX2D")
    size(640, 480, "processing.javafx.PGraphicsFX2D")
  }

  var obj = Vector[FieldObject]()
  var c = 0

  override def setup() = {
    background(0)
    smooth()
  }

  override def keyPressed() {
    if (key == '1') {
      obj :+= Soldier(this, (math.random() * width).toDouble, (math.random() * height).toDouble)
    }
  }

  override def draw() = {
    println(s"---- $frameCount ----")
    Display.refresh(0)

    obj = for (x <- obj if x.hp > 0) yield {
      x match {
        case y: MovableObject => y.move(1)
        case _ =>
      }
      x.draw()
      x
    }
  }

  // TODO: すべての関数の第一引数にthisを渡せば、外部オブジェクトでも使えるかも
  object Display {
    // 画面全体を塗りつぶします。
    def refresh(gray: Int): Unit = {
      fill(gray)
      rect(0, 0, width, height)
    }

    def refresh(r: Int, g: Int, b: Int): Unit = {
      fill(r, g, b)
      rect(0, 0, width, height)
    }

    def refresh(r: Int, g: Int, b: Int, a: Int): Unit = {
      fill(r, g, b, a)
      rect(0, 0, width, height)
    }
  }


}