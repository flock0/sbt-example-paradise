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
      val length: Byte) {
        def ===(o: String21): Boolean = compare(o) == 0
        def =!=(o: String21): Boolean = compare(o) != 0
        def compare(o: String21): Int =
            if (length == o.length &&
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

@data class TupleString(@embed val __1: String21, @embed val __2: String21, @embed val __3: String21, val length: Int) {
    def ===(o: TupleString): Boolean = compare(o) == 0
    def =!=(o: TupleString): Boolean = compare(o) != 0
    def compare(o: TupleString): Int =
        if (length == o.length)
            __1.compare(o.__1) + __2.compare(o.__2) + __3.compare(o.__3)
        else
            1
}
object TupleString {
  def apply(data: scala.Array[Byte])(implicit alloc: Allocator): TupleString = {
    var remaining = data.length
    val __1 =   if(remaining > 21)
                    String21(data, 0, 21)
                else
                    String21(data, 0, remaining.toByte)
    remaining -= 21
    val __2 =   if(remaining > 21)
                    String21(data, 21, 21)
                else
                              String21(data, 21, remaining.toByte)
    remaining -= 21
    val __3 =   if(remaining > 21) // Strings bigger than 21 * 3 characters are truncated
                    String21(data, 42, 21)
                else
                    String21(data, 42, remaining.toByte)
    TupleString(__1, __2, __3, __1.length + __2.length + __3.length)


  }
  def default(implicit alloc: Allocator) = apply("".getBytes)
}