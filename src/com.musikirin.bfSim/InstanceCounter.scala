package com.musikirin.bfSim

/**
  * Created by kirin on 2017/05/01.
  */
trait InstanceCounter {
  var count = 0

  def counter(): Int = {
    count += 1
    count - 1
  }
}
