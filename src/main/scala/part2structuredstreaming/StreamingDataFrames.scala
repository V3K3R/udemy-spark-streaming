package part2structuredstreaming

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._
import common._
import org.apache.spark.sql.streaming.Trigger
import scala.concurrent.duration._

object StreamingDataFrames {
  val sc: SparkSession = SparkSession
    .builder()
    .appName("app1")
    .master("local[2]")
    .getOrCreate()

  def readFromSocket(): Unit = {
    // reading a stream
    val lines: DataFrame = sc.readStream
      .format("socket")
      .option("host", "localhost")
      .option("port", 9999)
      .load()

    // transformation
    val shortLines: DataFrame = lines.filter(length(col("value")) < 10)

    // output stream
    val query = shortLines.writeStream
      .format("console")
      .outputMode("append")
      .trigger(Trigger.ProcessingTime(1.seconds))
      .start()

    query.awaitTermination()
  }

  def readFromFiles(): Unit = {
    val stocksDF: DataFrame = sc.readStream
      .format("csv")
      .option("header", "false")
      .option("dateFormat", "MMM d yyyy")
      .schema(stocksSchema)
      .load("src/main/resources/data/stocks/")

    stocksDF.writeStream
      .format("console")
      .outputMode("append")
      .start()
      .awaitTermination()
  }

  def main(args: Array[String]): Unit = {
    readFromSocket()
    // readFromFiles()
  }
}
