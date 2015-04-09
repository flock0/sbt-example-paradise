package ch.epfl.data
package legobase


import scala.collection.mutable.Set
import scala.collection.mutable.HashMap
import scala.collection.mutable.TreeSet
import scala.collection.mutable.ArrayBuffer
import storagemanager.K2DBScanner
import storagemanager.Loader
import queryengine.GenericEngine
import pardis.shallow.OptimalString
import pardis.shallow.scalalib.collection.Cont
import offheap._, x64._

class MultiMap[T, S] extends HashMap[T, Set[S]] with scala.collection.mutable.MultiMap[T, S]

object OrderingFactory {
  def apply[T](fun: (T, T) => Int): Ordering[T] = new Ordering[T] {
    def compare(o1: T, o2: T) = fun(o1, o2)
  }
}

object Q1 extends LegoRunner {

  implicit val m = NativeMemory()
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
    def apply(data: scala.Array[Byte], offset: Int, length: Byte): String21 = {
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
    def defaul = apply("".getBytes, 0, 0)
  }

  @data class OffheapString(val __1: String21, val __2: String21, val __3: String21, val length: Int) {
      def ===(o: OffheapString): Boolean = compare(o) == 0
      def =!=(o: OffheapString): Boolean = compare(o) != 0
      def compare(o: OffheapString): Int =
          if (length == o.length)
              __1.compare(o.__1) + __2.compare(o.__2) + __3.compare(o.__3)
          else
              1
  }
  object OffheapString {
    def apply(data: scala.Array[Byte]): OffheapString = {
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
      OffheapString(__1, __2, __3, __1.length + __2.length + __3.length)


    }
    def default = apply("".getBytes)
  }

  @data class LINEITEMRecord(val L_ORDERKEY: Int, val L_PARTKEY: Int, val L_SUPPKEY: Int, val L_LINENUMBER: Int, val L_QUANTITY: Int, val L_EXTENDEDPRICE: Double, val L_DISCOUNT: Double, val L_TAX: Double, val L_RETURNFLAG: Char, val L_LINESTATUS: Char, val L_SHIPDATE: Int, val L_COMMITDATE: Int, val L_RECEIPTDATE: Int, val L_SHIPINSTRUCT: OffheapString, val L_SHIPMODE: OffheapString, val L_COMMENT: OffheapString)
  case class GroupByClass(val L_RETURNFLAG: Char, val L_LINESTATUS: Char)
  case class AGGRecord_GroupByClass(val key: GroupByClass, val aggs: scala.Array[Double])

  def executeQuery(query: String, sf: Double): Unit = {;main()}
  def main(args: scala.Array[String]) {
    run(args)
  }

  def main() = 
  {
    val x1 = Loader.fileLineCount("/mnt/tpchdata/sf0.1/lineitem.tbl")
    val x2 = new K2DBScanner("/mnt/tpchdata/sf0.1/lineitem.tbl")
    val x3 = Array.uninit[LINEITEMRecord](x1)
    var i4: Int = 0
    val x49 = while({
      val x5 = x2.hasNext()
      x5
    })
    {
      val x6 = x2.next_int()
      val x7 = x2.next_int()
      val x8 = x2.next_int()
      val x9 = x2.next_int()
      val x10 = x2.next_int()
      val x11 = x2.next_double()
      val x12 = x2.next_double()
      val x13 = x2.next_double()
      val x14 = x2.next_char()
      val x15 = x2.next_char()
      val x16 = x2.next_date
      val x17 = x2.next_date
      val x18 = x2.next_date
      val x20 = new scala.Array[Byte](26)
      val x21 = x2.next(x20)
      val x24 = { x22: Byte => {
          val x23 = x22.!=(0)
          x23
        }
      }
      val x25 = x20.filter(x24)
      val x26 = OffheapString(x25)
      val x28 = new scala.Array[Byte](11)
      val x29 = x2.next(x28)
      val x32 = { x30: Byte => {
          val x31 = x30.!=(0)
          x31
        }
      }
      val x33 = x28.filter(x32)
      val x34 = OffheapString(x33)
      val x36 = new scala.Array[Byte](45)
      val x37 = x2.next(x36)
      val x40 = { x38: Byte => {
          val x39 = x38.!=(0)
          x39
        }
      }
      val x41 = x36.filter(x40)
      val x42 = OffheapString(x41)
      val x43 = LINEITEMRecord(x6, x7, x8, x9, x10, x11, x12, x13, x14, x15, x16, x17, x18, x26, x34, x42)
      val x44 = i4
      val x45 = x3.update(x44, x43)
      val x46 = i4
      val x47 = x46.+(1)
      val x48 = i4 = x47
      x48
    }
    val x50 = new Range(0, 1, 1)
    val x316 = { x51: Int => {
        val x431 = new HashMap[GroupByClass, AGGRecord_GroupByClass]()
        val x315 = GenericEngine.runQuery({
          val x52 = GenericEngine.parseDate("1998-09-02")
          var x373: Int = 0
          val x144 = { (x128: AGGRecord_GroupByClass, x129: AGGRecord_GroupByClass) => {
              val x130 = x128.key
              val x131 = x130.L_RETURNFLAG
              val x132 = x129.key
              val x133 = x132.L_RETURNFLAG
              val x134 = x131.-(x133)
              var res135: Int = x134
              val x136 = res135
              val x137 = x136.==(0)
              val x142 = if(x137) 
              {
                val x138 = x130.L_LINESTATUS
                val x139 = x132.L_LINESTATUS
                val x140 = x138.-(x139)
                val x141 = res135 = x140
                x141
              }
              else
              {
                ()
              }
              
              val x143 = res135
              x143
            }
          }
          val x478 = OrderingFactory(x144)
          val x479 = new TreeSet()(x478)
          var x500: Int = 0
          val x259 = while({
            val x167 = true.&&({
              val x1183 = x373
              val x166 = x1183.<(x1)
              x166
            })
            x167
          })
          {
            val x1185 = x373
            val x169 = x3.apply(x1185)
            val x171 = x169.L_SHIPDATE
            val x172 = x171.<=(x52)
            val x255 = if(x172) 
            {
              val x173 = x169.L_RETURNFLAG
              val x174 = x169.L_LINESTATUS
              val x175 = GroupByClass(x173, x174)
              val x179 = x431.getOrElseUpdate(x175, {
                val x177 = new scala.Array[Double](9)
                val x178 = AGGRecord_GroupByClass(x175, x177)
                x178
              })
              val x180 = x179.aggs
              val x194 = x180.apply(0)
              val x195 = x169.L_DISCOUNT
              val x196 = x195.+(x194)
              val x197 = x180.update(0, x196)
              val x204 = x180.apply(1)
              val x205 = x169.L_QUANTITY
              val x206 = x205.toDouble
              val x207 = x206.+(x204)
              val x208 = x180.update(1, x207)
              val x215 = x180.apply(2)
              val x216 = x169.L_EXTENDEDPRICE
              val x217 = x216.+(x215)
              val x218 = x180.update(2, x217)
              val x225 = x180.apply(3)
              val x226 = 1.0.-(x195)
              val x227 = x216.*(x226)
              val x228 = x227.+(x225)
              val x229 = x180.update(3, x228)
              val x236 = x180.apply(4)
              val x237 = x169.L_TAX
              val x238 = 1.0.+(x237)
              val x239 = x227.*(x238)
              val x240 = x239.+(x236)
              val x241 = x180.update(4, x240)
              val x248 = x180.apply(5)
              val x249 = x248.+(1)
              val x250 = x180.update(5, x249)
              ()
            }
            else
            {
              ()
            }
            
            val x1272 = x373
            val x257 = x1272.+(1)
            val x1274 = x373 = x257
            x1274
          }
          val x284 = { x261: Tuple2[GroupByClass, AGGRecord_GroupByClass] => {
              val x262 = x261._2
              val x266 = x262.aggs
              val x267 = x266.apply(1)
              val x268 = x266.apply(5)
              val x269 = x267./(x268)
              val x270 = x266.update(6, x269)
              val x272 = x266.apply(2)
              val x273 = x266.apply(5)
              val x274 = x272./(x273)
              val x275 = x266.update(7, x274)
              val x277 = x266.apply(0)
              val x278 = x266.apply(5)
              val x279 = x277./(x278)
              val x280 = x266.update(8, x279)
              val x283 = x479.+=(x262)
              ()
            }
          }
          val x285 = x431.foreach(x284)
          val x312 = while({
            val x291 = true.&&({
              val x289 = x479.size
              val x290 = x289.!=(0)
              x290
            })
            x291
          })
          {
            val x293 = x479.head
            val x294 = x479.-=(x293)
            val x296 = x293.key
            val x297 = x296.L_RETURNFLAG
            val x298 = x296.L_LINESTATUS
            val x299 = x293.aggs
            val x300 = x299.apply(1)
            val x301 = x299.apply(2)
            val x302 = x299.apply(3)
            val x303 = x299.apply(4)
            val x304 = x299.apply(6)
            val x305 = x299.apply(7)
            val x306 = x299.apply(8)
            val x307 = x299.apply(5)
            val x308 = printf("%c|%c|%.2f|%.2f|%.2f|%.2f|%.2f|%.2f|%.2f|%.0f\n", x297, x298, x300, x301, x302, x303, x304, x305, x306, x307)
            val x1321 = x500
            val x310 = x1321.+(1)
            val x1323 = x500 = x310
            x1323
          }
          val x1324 = x500
          val x314 = printf("(%d rows)\n", x1324)
          ()
        })
        x315
      }
    }
    val x317 = x50.foreach(x316)
    x317
  }
}