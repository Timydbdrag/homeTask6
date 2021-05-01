import Handler.getData
import io.circe.generic.auto._
import io.circe.syntax.EncoderOps
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import service.{KafkaHelper, TestCallback}

object Producer {

  private val TOPIC="books"
  private val PARTITION_COUNT = 3

  def main(args: Array[String]): Unit = {
    val producer:KafkaProducer[String,String] = KafkaHelper.createProducer()

    val callback = new TestCallback
    val dt = getData

    for(part <- 0 until PARTITION_COUNT){
      dt.forEach(el => {
        producer.send(new ProducerRecord(TOPIC, part, Integer.toString(part), el.asJson.spaces2), callback)
      })
    }

    producer.close()
  }

}
