import service.{KafkaHelper, MyCache}

import java.time.Duration
import java.util
import scala.jdk.CollectionConverters._

object Consumer {

  private val TOPIC = "books"

  def main(args: Array[String]): Unit = {
    val consumer = KafkaHelper.createConsumer()
    consumer.subscribe(util.Arrays.asList(TOPIC))

    val myQueue1 = new MyCache
    val myQueue2 = new MyCache
    val myQueue3 = new MyCache

    var numMsgReceived = 0

    import scala.util.control.Breaks._
    breakable {
      while (true) {
        val records = consumer.poll(Duration.ofSeconds(1)).asScala

        if (records.isEmpty) break

        for (r <- records) {
          numMsgReceived += 1
          if (r.partition() == 0) myQueue1.add("partition: " + r.partition() + ", offset: " + r.offset() + ", val: " + r.value())
          if (r.partition() == 1) myQueue2.add("partition: " + r.partition() + ", offset: " + r.offset() + ", val: " + r.value())
          if (r.partition() == 2) myQueue3.add("partition: " + r.partition() + ", offset: " + r.offset() + ", val: " + r.value())
        }
        //consumer.commitSync()
      }
    }
    if (myQueue1.get.nonEmpty || myQueue2.get.nonEmpty || myQueue3.get.nonEmpty) {
      println("=" * 40)
      myQueue1.get.foreach(println)
      myQueue2.get.foreach(println)
      myQueue3.get.foreach(println)
    }
  }
}
