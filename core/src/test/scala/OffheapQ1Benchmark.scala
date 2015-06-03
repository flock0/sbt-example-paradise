import org.scalameter.api._
import ch.epfl.data.dblab.legobase._
import off._
import java.io.File

object OffheapQ1Benchmark extends PerformanceTest {

  lazy val executor = SeparateJvmsExecutor(
    new Executor.Warmer.Default,
    Aggregator.min,
    new Measurer.Default)
  lazy val reporter = new LoggingReporter
  lazy val persistor = Persistor.None

  performance of "OffheapQ1" in {
    measure method "executeQuery" in {
      using(Gen.unit("OffheapQ1")) in { r =>
        OffheapQ1.executeQuery("", 0.1, ch.epfl.data.dblab.legobase.schema.Schema(List()))
      }
    }
  }
}
