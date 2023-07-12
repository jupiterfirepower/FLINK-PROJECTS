package org

import org.apache.flink.api.scala._
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.api.common.functions.FlatMapFunction
import org.apache.flink.core.fs.FileSystem

/**
 * Implements the "WordCount" program that computes a simple word occurrence histogram
 * over some sample data
 *   - write a simple Flink program.
 *   - use Tuple data types.
 *   - write and use user-defined functions.
 */

object WordCount {
  def main(args: Array[String]): Unit = {

    val params: ParameterTool = ParameterTool.fromArgs(args)

    // set up the execution environment
    val env = ExecutionEnvironment.getExecutionEnvironment
    // make parameters available in the web interface

    env.getConfig.setGlobalJobParameters(params)

    val text = env.readTextFile(params.get("input"))

    //val counts = text
    //  .flatMap(value => value.split("\\s+"))
    //  .filter(x => x.nonEmpty)
    //  .map(value => (value,1))
    //  .groupBy(0)
    //  .sum(1)

    val counts = text
        .flatMap { _.toLowerCase.split("\\W+") filter { _.nonEmpty } }
        .map { (_, 1) }
        .groupBy(0)
        .sum(1)

    counts.writeAsCsv(params.get("output"), "\n", " ", FileSystem.WriteMode.OVERWRITE)

    env.execute("Scala WordCount Example.")
  }
}
