import org.scalameter.api._
import ch.epfl.data.dblab.legobase._
object Q1Benchmark extends PerformanceTest {

  /* configuration */

  lazy val executor = SeparateJvmsExecutor(
    new Executor.Warmer.Default,
    Aggregator.min,
    new Measurer.Default)
  lazy val reporter = new LoggingReporter
  lazy val persistor = Persistor.None

  /* tests */

  performance of "Q1" in {
    measure method "executeQuery" in {
      Q1.executeQuery("", 0.1, ch.epfl.data.dblab.legobase.schema.Schema(List()))
    }
  }
}