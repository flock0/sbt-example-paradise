package ch.epfl.data
package dblab.legobase
package off

import sc.pardis.shallow.OptimalString
import ch.epfl.data.sc.pardis.annotations._
import offheap._

@deep
@noImplementation
@needs[(Tuple2[_, _], Array[_])]
@data
class OffheapOptimalString(@embed val data: Array[Byte]) {
  // override def hashCode(): Int = data.foldLeft(0)((sum, e) => sum + e)
  // override def equals(o: Any): Boolean = {
  //   val arr2 = o.asInstanceOf[OffheapOptimalString]
  //   data.sameElements(arr2.data)
  // }
  // def apply(i: Int): Byte = data(i)
  // def startsWith(o: OffheapOptimalString): Boolean = data.startsWith(o.data)
  // def containsSlice(o: OffheapOptimalString): Boolean = data.containsSlice(o.data)
  def endsWith(o: OffheapOptimalString): Boolean = ??? //data.endsWith(o.data)
  def endsWith(o: OptimalString): Boolean = ??? //data.endsWith(o.data)
  // def slice(start: Int, end: Int): OffheapOptimalString = OffheapOptimalString(data.slice(start, end))
  // def indexOfSlice(o: OffheapOptimalString, i: Int): Int = data.indexOfSlice(o.data, i)
  def diff(o: OffheapOptimalString): Int = ???
    // TODO: REFACTOR!
    // (data zip o.data).foldLeft(0)((res, e) => { if (res == 0) e._1 - e._2 else res })
  // def compare(o: OffheapOptimalString): Int = if (data.sameElements(o.data)) 0 else 1
  def ===(o: OffheapOptimalString): Boolean = ??? //compare(o) == 0
  def ===(o: OptimalString): Boolean = ??? //compare(o) == 0
  // def =!=(o: OffheapOptimalString): Boolean = compare(o) == 1
  // def +(o: Any): OffheapOptimalString = ??? /* Not used by the shallow implementation */
  // def length: Int = data.length
  def string: String = new String(data.toArray.map(_.toChar))
  // def zip(o: OffheapOptimalString) = data.zip(o.data)
  // def foldLeft(c: Int)(f: (Int, Byte) => Int): Int = data.foldLeft(c)(f)
  // def reverse(): OffheapOptimalString = OffheapOptimalString(data.reverse)
  // def split(delimiter: scala.Array[Char]): scala.Array[OffheapOptimalString] = {
  //   val str = new String(data.map(_.toChar))
  //   val res = str.split(delimiter)
  //   res.map(s => OffheapOptimalString(s.getBytes))
  // }
  // override def toString = string
}

object OffheapOptimalString {
  def apply(data: scala.Array[Byte]): OffheapOptimalString = new OffheapOptimalString(Array.fromArray[Byte](data)(malloc))
  def default = apply("".getBytes)
}
