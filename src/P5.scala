import com.musikirin.bfSim._
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

  override def setup() = {
    background(0)
    smooth()
  }

  override def draw() = {
    //    println(s"---- $frameCount ----")
    Display.refresh(0)

    // ガード文でHP0のオブジェクトを排除します。
    obj = for (x <- obj if x.hp > 0) yield {
      x match {
        case y: MovableObject => y.move(1)
        case _ =>
      }
      x.draw()
      x
    }

    gabage = for (x <- gabage if x.hp > 0) yield {
      x.draw()
      x
    }

    collusion()
  }

  override def keyPressed() {
    if (key == '1') {
      obj :+= Soldier(this, (math.random() * width).toDouble, (math.random() * height).toDouble)
    }
    if (key == '0') {
      for (x <- obj) yield {
        x match {
          case x: Soldier => obj :+= x.fire()
          case _ =>
        }
      }
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