import org.scalameter.api._
import ch.epfl.data.dblab.legobase._
import off._

object Q6Benchmark extends PerformanceTest {

  lazy val executor = LocalExecutor(
    new Executor.Warmer.Default,
    Aggregator.min,
    new Measurer.Default)
  lazy val reporter = new LoggingReporter
  lazy val persistor = Persistor.None

  performance of "Q6" in {
    measure method "executeQuery" in {
      using(Gen.unit("Q6")) in { r =>
        Q6.executeQuery("", 0.1, ch.epfl.data.dblab.legobase.schema.Schema(List()))
      }
    }
  }

  performance of "OffheapQ6" in {
    measure method "executeQuery" in {
      using(Gen.unit("OffheapQ6")) in { r =>
        OffheapQ6.executeQuery("", 0.1, ch.epfl.data.dblab.legobase.schema.Schema(List()))
      }
    }
  }
}