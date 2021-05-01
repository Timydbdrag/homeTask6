package service

import org.apache.kafka.clients.producer.{Callback, RecordMetadata}

class TestCallback extends Callback {
  def onCompletion(recordMetadata: RecordMetadata, e: Exception): Unit = {
    if (e != null) {
      println("Error while producing message to topic :" + recordMetadata)
      e.printStackTrace()
    }
    else {
      val message = String.format("sent message to topic:%s partition:%s  offset:%s",
        recordMetadata.topic, recordMetadata.partition, recordMetadata.offset)
      println(message)
    }
  }
}