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

    // デバッグ用兵士自動生成
    for (_ <- 0 until 5) {
      soldiers_list :+= Soldier(this, math.random() * width / 4, math.random() * height, 0, 0)
      soldiers_list :+= Soldier(this, width - (math.random() * width / 4), math.random() * height, 1, 1)
    }
  }

  override def draw() = {
    //    println(s"---- $frameCount ----")
    Display.refresh(0)

    // ガード文でHP0のオブジェクトを排除します。
    soldiers_list = for (x <- soldiers_list if x.hp > 0) yield {
      x.think()
      println(x.id + " : " + x.hp + " / " + x.max_hp)
      x
    }

    bullets_list = for (x <- bullets_list if x.hp > 0) yield {
      x.move(1)
      x.draw()
      x
    }

    gabage = for (x <- gabage if x.hp > 0) yield {
      x.draw()
      x
    }
  }

  // キーの入力による動作
  override def keyPressed() {
    if (key == '1') {
      soldiers_list :+= Soldier(this, math.random() * width / 4, math.random() * height, 0, 0)
    }
    if (key == '2') {
      soldiers_list :+= Soldier(this, width - (math.random() * width / 4), math.random() * height, 1, 1)
    }
    if (key == '0') {
      for (x <- soldiers_list) x.fire()
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