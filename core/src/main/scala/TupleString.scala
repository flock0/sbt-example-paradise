package ch.epfl.data
package dblab.legobase
package off

import offheap._

@data class String21(
      val __1: Byte,
      val __2: Byte,
      val __3: Byte,
      val __4: Byte,
      val __5: Byte,
      val __6: Byte,
      val __7: Byte,
      val __8: Byte,
      val __9: Byte,
      val __10: Byte,
      val __11: Byte,
      val __12: Byte,
      val __13: Byte,
      val __14: Byte,
      val __15: Byte,
      val __16: Byte,
      val __17: Byte,
      val __18: Byte,
      val __19: Byte,
      val __20: Byte,
      val __21: Byte,
      val length: Byte) extends Iterable[Byte] {

        override def iterator = new Iterator[Byte] {
          var currentElement = 1
          def hasNext = currentElement <= length
          def next = {
            currentElement += 1
            apply(currentElement - 1)
          }
        }

        def ===(o: String21): Boolean = compare(o) == 0
        def =!=(o: String21): Boolean = compare(o) != 0
        def compare(o: String21): Int =
        	if (length == 0 && o.length == 0)
            0
          else if (length == o.length &&
              __1 == o.__1 &&
              __2 == o.__2 &&
              __3 == o.__3 &&
              __4 == o.__4 &&
              __5 == o.__5 &&
              __6 == o.__6 &&
              __7 == o.__7 &&
              __8 == o.__8 &&
              __9 == o.__9 &&
              __10 == o.__10 &&
              __11 == o.__11 &&
              __12 == o.__12 &&
              __13 == o.__13 &&
              __14 == o.__14 &&
              __15 == o.__15 &&
              __16 == o.__16 &&
              __17 == o.__17 &&
              __18 == o.__18 &&
              __19 == o.__19 &&
              __20 == o.__20 &&
              __21 == o.__21)
              0
          else
              1

      def apply(i: Int): Byte = i match {
        case 1 => __1
        case 2 => __2
        case 3 => __3
        case 4 => __4
        case 5 => __5
        case 6 => __6
        case 7 => __7
        case 8 => __8
        case 9 => __9
        case 10 => __10
        case 11 => __11
        case 12 => __12
        case 13 => __13
        case 14 => __14
        case 15 => __15
        case 16 => __16
        case 17 => __17
        case 18 => __18
        case 19 => __19
        case 20 => __20
        case 21 => __21
        case _ => throw new NoSuchElementException(s"no element with index $i")
      }
}

object String21 {
  def apply(data: scala.Array[Byte], offset: Int, length: Byte)(implicit alloc: Allocator): String21 = {
    val trimmed = data.drop(offset).take(length)
    val filled = if (length < 21)
                      trimmed ++ scala.Array.fill(21 - length)(0:Byte)
                   else
                      trimmed
  String21(filled(0), filled(1), filled(2), filled(3), filled(4), filled(5), 
    filled(6), filled(7), filled(8), filled(9), filled(10), filled(11), 
    filled(12), filled(13), filled(14), filled(15), filled(16), filled(17), 
    filled(18), filled(19), filled(20), length)
  }
  def default(implicit alloc: Allocator) = apply("".getBytes, 0, 0)
}

@data class TupleString(
  @embed val __1: String21, 
  @embed val __2: String21, 
  @embed val __3: String21, 
  @embed val __4: String21, 
  @embed val __5: String21, 
  @embed val __6: String21, 
  @embed val __7: String21, 
  @embed val __8: String21, 
  @embed val __9: String21, 
  @embed val __10: String21, 
  val length: Int) extends Iterable[Byte] {

  override def iterator = __1.iterator ++ __2.iterator ++ __3.iterator ++ 
                 __4.iterator ++ __5.iterator ++ __6.iterator ++ 
                 __7.iterator ++ __8.iterator ++ __9.iterator ++ 
                 __10.iterator

  def endsWith(that: TupleString): Boolean = {
    val i = this.iterator.drop(length - that.length)
    val j = that.iterator
    while (i.hasNext && j.hasNext)
      if (i.next != j.next)
        return false

    !j.hasNext
  }

  def endsWith(that: OptimalString): Boolean = {
    val i = this.iterator.drop(length - that.length)
    val j = that.data.iterator
    while (i.hasNext && j.hasNext)
      if (i.next != j.next)
        return false

    !j.hasNext
  }

  def diff(that: TupleString): Int = (this.iterator zip that.iterator).foldLeft(0)((res, e) => { if (res == 0) e._1 - e._2 else res })
  def ===(that: TupleString): Boolean = compare(that) == 0
  def =!=(that: TupleString): Boolean = compare(that) != 0
  def compare(that: TupleString): Int =
      if (length == that.length)
          __1.compare(that.__1) + __2.compare(that.__2) + __3.compare(that.__3) + 
          __4.compare(that.__4) + __5.compare(that.__5) + __6.compare(that.__6) + 
          __7.compare(that.__7) + __8.compare(that.__8) + __9.compare(that.__9) + 
          __10.compare(that.__10)
      else
          1

  def string: String = {
    val sb = StringBuilder()
    iterator.foreach(b => sb.append(b))
    sb.toString
  }

  override def toString = string
}
object TupleString {
  def apply(data: scala.Array[Byte])(implicit alloc: Allocator): TupleString = {
    var remaining = data.length
    val __1 = if(remaining > 21)
                String21(data, 0 * 21, 21)
              else {
                String21(data, 0 * 21, remaining.toByte)
                remaining = 0
              }
    remaining -= 21
    val __2 = if(remaining > 21)
                String21(data, 1 * 21, 21)
              else {
                String21(data, 1 * 21, remaining.toByte)
                remaining = 0
              }
    remaining -= 21
    val __3 = if(remaining > 21)
                String21(data, 2 * 21, 21)
              else {
                String21(data, 2 * 21, remaining.toByte)
                remaining = 0
              }
    remaining -= 21
    val __4 = if(remaining > 21)
                String21(data, 3 * 21, 21)
              else {
                String21(data, 3 * 21, remaining.toByte)
                remaining = 0
              }
    remaining -= 21
    val __5 = if(remaining > 21)
                String21(data, 4 * 21, 21)
              else {
                String21(data, 4 * 21, remaining.toByte)
                remaining = 0
              }
    remaining -= 21
    val __6 = if(remaining > 21)
                String21(data, 5 * 21, 21)
              else {
                String21(data, 5 * 21, remaining.toByte)
                remaining = 0
              }
    remaining -= 21
    val __7 = if(remaining > 21)
                String21(data, 6 * 21, 21)
              else {
                String21(data, 6 * 21, remaining.toByte)
                remaining = 0
              }
    remaining -= 21
    val __8 = if(remaining > 21)
                String21(data, 7 * 21, 21)
              else {
                String21(data, 7 * 21, remaining.toByte)
                remaining = 0
              }
    remaining -= 21
    val __9 = if(remaining > 21)
                String21(data, 8 * 21, 21)
              else {
                String21(data, 8 * 21, remaining.toByte)
                remaining = 0
              }
    remaining -= 21
    val __10 = if(remaining > 21)
                String21(data, 9 * 21, 21)
              else {
                String21(data, 9 * 21, remaining.toByte)
                remaining = 0
              }
    TupleString(__1, __2, __3, __4, __5, __6, __7, __8, __9, __10, 
      __1.length + __2.length + __3.length + 
      __4.length + __5.length + __6.length + 
      __7.length + __8.length + __9.length + 
      __10.length)
  }
  def default(implicit alloc: Allocator) = apply("".getBytes)
}