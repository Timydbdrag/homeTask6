package service

import scala.collection.mutable

class MyCache {
  private val queue = mutable.Queue[String]()

  def add(x: String): Unit = {
    if (queue.size > 4) queue.dequeue()
    queue.enqueue(x)
  }

  def get: mutable.Queue[String] = {
    queue
  }

}