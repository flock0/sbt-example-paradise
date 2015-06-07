package ch.epfl.data
package dblab.legobase
package off

import scala.collection.mutable.Set
import scala.collection.mutable.HashMap
import scala.collection.mutable.TreeSet
import scala.collection.mutable.ArrayBuffer
import storagemanager.K2DBScanner
import storagemanager.Loader
import queryengine.GenericEngine
import sc.pardis.shallow.OptimalString
import sc.pardis.shallow.scalalib.collection.Cont
import offheap._

@data class String21(
      val __0: Byte,
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
      val len: Byte) {

      def iterator = new Iterator[Byte] {
        var currentElement = 0
        def hasNext = currentElement < len
        def next = {
          if(!hasNext)
            Iterator.empty.next
          val b = apply(currentElement)
          currentElement += 1
          b
        }
      }

      def ===(o: String21): Boolean = this.iterator.sameElements(o.iterator)
      def =!=(o: String21): Boolean = !(===(o))

      def apply(i: Int): Byte = 
        if (i >= len)
          throw new NoSuchElementException(s"no element with index $i")
        else i match {
          case 0 => __0
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
          case _ => throw new NoSuchElementException(s"no element with index $i")
        }

      def string: String = {
        val sb = new StringBuilder()
        this.iterator.foreach{b => sb += b.toChar}
        sb.toString
      }
}

object String21 {
  def apply(data: scala.Array[Byte], offset: Int, length: Byte)(implicit alloc: Allocator): String21 = {
    if (length > 21)
      throw new IllegalArgumentException("String21 can only take up to 21 bytes")
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
  @embed val __0: String21, 
  @embed val __1: String21, 
  @embed val __2: String21, 
  @embed val __3: String21, 
  @embed val __4: String21, 
  @embed val __5: String21, 
  @embed val __6: String21, 
  @embed val __7: String21, 
  @embed val __8: String21, 
  @embed val __9: String21, 
  val length: Int) {

  def iterator = __0.iterator ++ 
                 __1.iterator ++ __2.iterator ++ __3.iterator ++ 
                 __4.iterator ++ __5.iterator ++ __6.iterator ++ 
                 __7.iterator ++ __8.iterator ++ __9.iterator 
                 
  def apply(i: Int): Byte = ???
  def startsWith(o: OptimalString): Boolean = ???
  def containsSlice(o: OptimalString): Boolean = ???
  def slice(start: Int, end: Int): OptimalString = ???
  def indexOfSlice(o: OptimalString, i: Int): Int = ???
  def endsWith(that: TupleString): Boolean = this.iterator.drop(length - that.length).sameElements(that.iterator)
  def endsWith(that: OptimalString): Boolean = this.iterator.drop(length - that.data.length).sameElements(that.data.iterator)

  def diff(that: TupleString): Int = (this.iterator zip that.iterator).foldLeft(0)((res, e) => { if (res == 0) e._1 - e._2 else res })
  def ===(that: TupleString): Boolean = this.iterator.sameElements(that.iterator)
  def ===(that: OptimalString): Boolean = this.iterator.sameElements(that.data.iterator)
  def =!=(that: TupleString): Boolean = !(===(that))
  def =!=(that: OptimalString): Boolean = !(===(that))
  def zip(o: OptimalString) = ???
  def foldLeft(c: Int)(f: (Int, Byte) => Int): Int = ???
  def reverse(): OptimalString = ???
  def split(delimiter: Array[Char]): Array[OptimalString] = ???
  def string: String = {
    val sb = new StringBuilder()
    this.iterator.foreach{b => sb += b.toChar}
    sb.toString
  } 
}
object TupleString {
  def apply(data: scala.Array[Byte])(implicit alloc: Allocator): TupleString = {
    if (data.length > 210)
      throw new IllegalArgumentException("data is longer than 210 characters")
    var remaining = data.length

    val __0 = if (remaining <= 0) {
                String21(data, 0 * 21, 0)
              } else if (remaining <= 21) {
                val str = String21(data, 0 * 21, remaining.toByte)
                remaining = 0
                str
              } else {
                val str = String21(data, 0 * 21, 21)
                remaining -= 21
                str
              }
    val __1 = if (remaining <= 0) {
                String21(data, 1 * 21, 0)
              } else if (remaining <= 21) {
                val str = String21(data, 1 * 21, remaining.toByte)
                remaining = 0
                str
              } else {
                val str = String21(data, 1 * 21, 21)
                remaining -= 21
                str
              }
    val __2 = if (remaining <= 0) {
                String21(data, 2 * 21, 0)
              } else if (remaining <= 21) {
                val str = String21(data, 2 * 21, remaining.toByte)
                remaining = 0
                str
              } else {
                val str = String21(data, 2 * 21, 21)
                remaining -= 21
                str
              }
    val __3 = if (remaining <= 0) {
                String21(data, 3 * 21, 0)
              } else if (remaining <= 21) {
                val str = String21(data, 3 * 21, remaining.toByte)
                remaining = 0
                str
              } else {
                val str = String21(data, 3 * 21, 21)
                remaining -= 21
                str
              }
    val __4 = if (remaining <= 0) {
                String21(data, 4 * 21, 0)
              } else if (remaining <= 21) {
                val str = String21(data, 4 * 21, remaining.toByte)
                remaining = 0
                str
              } else {
                val str = String21(data, 4 * 21, 21)
                remaining -= 21
                str
              }
    val __5 = if (remaining <= 0) {
                String21(data, 5 * 21, 0)
              } else if (remaining <= 21) {
                val str = String21(data, 5 * 21, remaining.toByte)
                remaining = 0
                str
              } else {
                val str = String21(data, 5 * 21, 21)
                remaining -= 21
                str
              }
    val __6 = if (remaining <= 0) {
                String21(data, 6 * 21, 0)
              } else if (remaining <= 21) {
                val str = String21(data, 6 * 21, remaining.toByte)
                remaining = 0
                str
              } else {
                val str = String21(data, 6 * 21, 21)
                remaining -= 21
                str
              }
    val __7 = if (remaining <= 0) {
                String21(data, 7 * 21, 0)
              } else if (remaining <= 21) {
                val str = String21(data, 7 * 21, remaining.toByte)
                remaining = 0
                str
              } else {
                val str = String21(data, 7 * 21, 21)
                remaining -= 21
                str
              }
    val __8 = if (remaining <= 0) {
                String21(data, 8 * 21, 0)
              } else if (remaining <= 21) {
                val str = String21(data, 8 * 21, remaining.toByte)
                remaining = 0
                str
              } else {
                val str = String21(data, 8 * 21, 21)
                remaining -= 21
                str
              }
    val __9 = if (remaining <= 0) {
                String21(data, 9 * 21, 0)
              } else if (remaining <= 21) {
                val str = String21(data, 9 * 21, remaining.toByte)
                remaining = 0
                str
              } else {
                val str = String21(data, 9 * 21, 21)
                remaining -= 21
                str
              }
    TupleString(__0, __1, __2, __3, __4, __5, __6, __7, __8, __9, 
      __0.len + __1.len + __2.len +
      __3.len + __4.len + __5.len +
      __6.len + __7.len + __8.len +
      __9.len)
  }
  def default(implicit alloc: Allocator) = apply("".getBytes)
}

@data class REGIONRecord(val R_REGIONKEY: Int, @embed val R_NAME: TupleString)
@data class NATIONRecord(val N_NATIONKEY: Int, @embed val N_NAME: TupleString, val N_REGIONKEY: Int)
@data class PARTRecord(val P_PARTKEY: Int, @embed val P_MFGR: TupleString, @embed val P_TYPE: TupleString, val P_SIZE: Int)
@data class SUPPLIERRecord(val S_SUPPKEY: Int, @embed val S_NAME: TupleString, @embed val S_ADDRESS: TupleString, val S_NATIONKEY: Int, @embed val S_PHONE: TupleString, val S_ACCTBAL: Double, @embed val S_COMMENT: TupleString)
@data class PARTSUPPRecord(val PS_PARTKEY: Int, val PS_SUPPKEY: Int, val PS_SUPPLYCOST: Double)

case class WindowRecord_Int_DynamicCompositeRecord_REGIONRecord_DynamicCompositeRecord_PARTRecord_DynamicCompositeRecord_NATIONRecord_DynamicCompositeRecord_SUPPLIERRecord_PARTSUPPRecord(val wnd: REGIONRecord_PARTRecord_NATIONRecord_SUPPLIERRecord_PARTSUPPRecord)
case class REGIONRecord_PARTRecord_NATIONRecord_SUPPLIERRecord_PARTSUPPRecord(val R_REGIONKEY: Int, val R_NAME: TupleString, val P_PARTKEY: Int, val P_MFGR: TupleString, val P_TYPE: TupleString, val P_SIZE: Int, val N_NATIONKEY: Int, val N_NAME: TupleString, val N_REGIONKEY: Int, val S_SUPPKEY: Int, val S_NAME: TupleString, val S_ADDRESS: TupleString, val S_NATIONKEY: Int, val S_PHONE: TupleString, val S_ACCTBAL: Double, val S_COMMENT: TupleString, val PS_PARTKEY: Int, val PS_SUPPKEY: Int, val PS_SUPPLYCOST: Double)
object OffheapQ2 extends LegoRunner {
  def executeQuery(query: String, sf: Double, schema: ch.epfl.data.dblab.legobase.schema.Schema): Unit = main()
  def main(args: scala.Array[String]) {
    run(args)
  }
  def main() = 
  {
    implicit val alloc = malloc
    val x1 = Loader.fileLineCount("/home/florian/Documents/tpch_testdata/sf0.1/part.tbl")
    val x2 = Array.uninit[PARTRecord](x1)
    val x3 = new K2DBScanner("/home/florian/Documents/tpch_testdata/sf0.1/part.tbl")
    var x4: Int = 0
    val x64 = while({
      val x5 = x4
      val x6 = x5.<(x1)
      val x8 = x6.&&({
        val x7 = x3.hasNext()
        x7
      })
      x8
    })
    {
      val x9 = x3.next_int()
      val x11 = new scala.Array[Byte](56)
      val x12 = x3.next(x11)
      val x15 = { x13: Byte => {
          val x14 = x13.!=(0)
          x14
        }
      }
      val x16 = x11.filter(x15)
      val x17 = TupleString(x16)
      val x19 = new scala.Array[Byte](26)
      val x20 = x3.next(x19)
      val x23 = { x21: Byte => {
          val x22 = x21.!=(0)
          x22
        }
      }
      val x24 = x19.filter(x23)
      val x25 = TupleString(x24)
      val x27 = new scala.Array[Byte](11)
      val x28 = x3.next(x27)
      val x31 = { x29: Byte => {
          val x30 = x29.!=(0)
          x30
        }
      }
      val x32 = x27.filter(x31)
      val x33 = TupleString(x32)
      val x34 = new scala.Array[Byte](26)
      val x35 = x3.next(x34)
      val x38 = { x36: Byte => {
          val x37 = x36.!=(0)
          x37
        }
      }
      val x39 = x34.filter(x38)
      val x40 = TupleString(x39)
      val x41 = x3.next_int()
      val x42 = new scala.Array[Byte](11)
      val x43 = x3.next(x42)
      val x46 = { x44: Byte => {
          val x45 = x44.!=(0)
          x45
        }
      }
      val x47 = x42.filter(x46)
      val x48 = TupleString(x47)
      val x49 = x3.next_double()
      val x51 = new scala.Array[Byte](24)
      val x52 = x3.next(x51)
      val x55 = { x53: Byte => {
          val x54 = x53.!=(0)
          x54
        }
      }
      val x56 = x51.filter(x55)
      val x57 = TupleString(x56)
      val x58 = PARTRecord(x9, x25, x40, x41)
      val x59 = x4
      val x60 = x2.update(x59, x58)
      val x61 = x4
      val x62 = x61.+(1)
      val x63 = x4 = x62
      x63
    }
    val x65 = Loader.fileLineCount("/home/florian/Documents/tpch_testdata/sf0.1/partsupp.tbl")
    val x66 = Array.uninit[PARTSUPPRecord](x65)
    val x67 = new K2DBScanner("/home/florian/Documents/tpch_testdata/sf0.1/partsupp.tbl")
    var x68: Int = 0
    val x91 = while({
      val x69 = x68
      val x70 = x69.<(x65)
      val x72 = x70.&&({
        val x71 = x67.hasNext()
        x71
      })
      x72
    })
    {
      val x73 = x67.next_int()
      val x74 = x67.next_int()
      val x75 = x67.next_int()
      val x76 = x67.next_double()
      val x78 = new scala.Array[Byte](200)
      val x79 = x67.next(x78)
      val x82 = { x80: Byte => {
          val x81 = x80.!=(0)
          x81
        }
      }
      val x83 = x78.filter(x82)
      val x84 = TupleString(x83)
      val x85 = PARTSUPPRecord(x73, x74, x76)
      val x86 = x68
      val x87 = x66.update(x86, x85)
      val x88 = x68
      val x89 = x88.+(1)
      val x90 = x68 = x89
      x90
    }
    val x92 = Loader.fileLineCount("/home/florian/Documents/tpch_testdata/sf0.1/nation.tbl")
    val x93 = Array.uninit[NATIONRecord](x92)
    val x94 = new K2DBScanner("/home/florian/Documents/tpch_testdata/sf0.1/nation.tbl")
    var x95: Int = 0
    val x124 = while({
      val x96 = x95
      val x97 = x96.<(x92)
      val x99 = x97.&&({
        val x98 = x94.hasNext()
        x98
      })
      x99
    })
    {
      val x100 = x94.next_int()
      val x102 = new scala.Array[Byte](26)
      val x103 = x94.next(x102)
      val x106 = { x104: Byte => {
          val x105 = x104.!=(0)
          x105
        }
      }
      val x107 = x102.filter(x106)
      val x108 = TupleString(x107)
      val x109 = x94.next_int()
      val x111 = new scala.Array[Byte](153)
      val x112 = x94.next(x111)
      val x115 = { x113: Byte => {
          val x114 = x113.!=(0)
          x114
        }
      }
      val x116 = x111.filter(x115)
      val x117 = TupleString(x116)
      val x118 = NATIONRecord(x100, x108, x109)
      val x119 = x95
      val x120 = x93.update(x119, x118)
      val x121 = x95
      val x122 = x121.+(1)
      val x123 = x95 = x122
      x123
    }
    val x125 = Loader.fileLineCount("/home/florian/Documents/tpch_testdata/sf0.1/region.tbl")
    val x126 = Array.uninit[REGIONRecord](x125)
    val x127 = new K2DBScanner("/home/florian/Documents/tpch_testdata/sf0.1/region.tbl")
    var x128: Int = 0
    val x156 = while({
      val x129 = x128
      val x130 = x129.<(x125)
      val x132 = x130.&&({
        val x131 = x127.hasNext()
        x131
      })
      x132
    })
    {
      val x133 = x127.next_int()
      val x135 = new scala.Array[Byte](26)
      val x136 = x127.next(x135)
      val x139 = { x137: Byte => {
          val x138 = x137.!=(0)
          x138
        }
      }
      val x140 = x135.filter(x139)
      val x141 = TupleString(x140)
      val x143 = new scala.Array[Byte](153)
      val x144 = x127.next(x143)
      val x147 = { x145: Byte => {
          val x146 = x145.!=(0)
          x146
        }
      }
      val x148 = x143.filter(x147)
      val x149 = TupleString(x148)
      val x150 = REGIONRecord(x133, x141)
      val x151 = x128
      val x152 = x126.update(x151, x150)
      val x153 = x128
      val x154 = x153.+(1)
      val x155 = x128 = x154
      x155
    }
    val x157 = Loader.fileLineCount("/home/florian/Documents/tpch_testdata/sf0.1/supplier.tbl")
    val x158 = Array.uninit[SUPPLIERRecord](x157)
    val x159 = new K2DBScanner("/home/florian/Documents/tpch_testdata/sf0.1/supplier.tbl")
    var x160: Int = 0
    val x206 = while({
      val x161 = x160
      val x162 = x161.<(x157)
      val x164 = x162.&&({
        val x163 = x159.hasNext()
        x163
      })
      x164
    })
    {
      val x165 = x159.next_int()
      val x167 = new scala.Array[Byte](26)
      val x168 = x159.next(x167)
      val x171 = { x169: Byte => {
          val x170 = x169.!=(0)
          x170
        }
      }
      val x172 = x167.filter(x171)
      val x173 = TupleString(x172)
      val x175 = new scala.Array[Byte](41)
      val x176 = x159.next(x175)
      val x179 = { x177: Byte => {
          val x178 = x177.!=(0)
          x178
        }
      }
      val x180 = x175.filter(x179)
      val x181 = TupleString(x180)
      val x182 = x159.next_int()
      val x184 = new scala.Array[Byte](16)
      val x185 = x159.next(x184)
      val x188 = { x186: Byte => {
          val x187 = x186.!=(0)
          x187
        }
      }
      val x189 = x184.filter(x188)
      val x190 = TupleString(x189)
      val x191 = x159.next_double()
      val x193 = new scala.Array[Byte](102)
      val x194 = x159.next(x193)
      val x197 = { x195: Byte => {
          val x196 = x195.!=(0)
          x196
        }
      }
      val x198 = x193.filter(x197)
      val x199 = TupleString(x198)
      val x200 = SUPPLIERRecord(x165, x173, x181, x182, x190, x191, x199)
      val x201 = x160
      val x202 = x158.update(x201, x200)
      val x203 = x160
      val x204 = x203.+(1)
      val x205 = x160 = x204
      x205
    }
    val x207 = new Range(0, 1, 1)
    val x863 = { x208: Int => {
        val x1204 = new MultiMap[Int, REGIONRecord_PARTRecord_NATIONRecord_SUPPLIERRecord_PARTSUPPRecord]()
        val x1189 = new MultiMap[Int, REGIONRecord]()
        val x1158 = new MultiMap[Int, PARTRecord]()
        val x1124 = new MultiMap[Int, NATIONRecord]()
        val x1101 = new MultiMap[Int, SUPPLIERRecord]()
        val x862 = GenericEngine.runQuery({
          val x209 = GenericEngine.parseString("AFRICA")
          val x210 = GenericEngine.parseString("TIN")
          var x1078: Int = 0
          var x1083: Int = 0
          var x1106: Int = 0
          var x1129: Int = 0
          var x1163: Int = 0
          val x320 = { (x291: WindowRecord_Int_DynamicCompositeRecord_REGIONRecord_DynamicCompositeRecord_PARTRecord_DynamicCompositeRecord_NATIONRecord_DynamicCompositeRecord_SUPPLIERRecord_PARTSUPPRecord, x292: WindowRecord_Int_DynamicCompositeRecord_REGIONRecord_DynamicCompositeRecord_PARTRecord_DynamicCompositeRecord_NATIONRecord_DynamicCompositeRecord_SUPPLIERRecord_PARTSUPPRecord) => {
              val x293 = x291.wnd
              val x294 = x293.S_ACCTBAL
              val x295 = x292.wnd
              val x296 = x295.S_ACCTBAL
              val x297 = x294.<(x296)
              val x319 = if(x297) 
              {
                1
              }
              else
              {
                val x298 = x294.>(x296)
                val x318 = if(x298) 
                {
                  -1
                }
                else
                {
                  val x299 = x293.N_NAME
                  val x300 = x295.N_NAME
                  val x301 = x299.diff(x300)
                  var res302: Int = x301
                  val x303 = res302
                  val x304 = x303.==(0)
                  val x316 = if(x304) 
                  {
                    val x305 = x293.S_NAME
                    val x306 = x295.S_NAME
                    val x307 = x305.diff(x306)
                    val x308 = res302 = x307
                    val x309 = res302
                    val x310 = x309.==(0)
                    val x315 = if(x310) 
                    {
                      val x311 = x293.P_PARTKEY
                      val x312 = x295.P_PARTKEY
                      val x313 = x311.-(x312)
                      val x314 = res302 = x313
                      x314
                    }
                    else
                    {
                      ()
                    }
                    
                    x315
                  }
                  else
                  {
                    ()
                  }
                  
                  val x317 = res302
                  x317
                }
                
                x318
              }
              
              x319
            }
          }
          val x1239 = OrderingFactory(x320)
          val x1240 = new TreeSet()(x1239)
          var x1241: Boolean = false
          var j322: Int = 0
          var x1270: Int = 0
          val x365 = while({
            val x352 = true.&&({
              val x4022 = x1163
              val x351 = x4022.<(x125)
              x351
            })
            x352
          })
          {
            val x4024 = x1163
            val x354 = x126.apply(x4024)
            val x356 = x354.R_NAME
            val x357 = x356.===(x209)
            val x361 = if(x357) 
            {
              val x358 = x354.R_REGIONKEY
              val x360 = x1189.addBinding(x358, x354)
              ()
            }
            else
            {
              ()
            }
            
            val x4032 = x1163
            val x363 = x4032.+(1)
            val x4034 = x1163 = x363
            x4034
          }
          val x386 = while({
            val x370 = true.&&({
              val x4039 = x1129
              val x369 = x4039.<(x1)
              x369
            })
            x370
          })
          {
            val x4041 = x1129
            val x372 = x2.apply(x4041)
            val x374 = x372.P_SIZE
            val x375 = x374.==(43)
            val x378 = x375.&&({
              val x376 = x372.P_TYPE
              val x377 = x376.endsWith(x210)
              x377
            })
            val x382 = if(x378) 
            {
              val x379 = x372.P_PARTKEY
              val x381 = x1158.addBinding(x379, x372)
              ()
            }
            else
            {
              ()
            }
            
            val x4052 = x1129
            val x384 = x4052.+(1)
            val x4054 = x1129 = x384
            x4054
          }
          val x401 = while({
            val x391 = true.&&({
              val x4059 = x1106
              val x390 = x4059.<(x92)
              x390
            })
            x391
          })
          {
            val x4061 = x1106
            val x393 = x93.apply(x4061)
            val x395 = x393.N_NATIONKEY
            val x397 = x1124.addBinding(x395, x393)
            val x4066 = x1106
            val x399 = x4066.+(1)
            val x4068 = x1106 = x399
            x4068
          }
          val x416 = while({
            val x406 = true.&&({
              val x4073 = x1083
              val x405 = x4073.<(x157)
              x405
            })
            x406
          })
          {
            val x4075 = x1083
            val x408 = x158.apply(x4075)
            val x410 = x408.S_SUPPKEY
            val x412 = x1101.addBinding(x410, x408)
            val x4080 = x1083
            val x414 = x4080.+(1)
            val x4082 = x1083 = x414
            x4082
          }
          val x806 = while({
            val x421 = true.&&({
              val x4087 = x1078
              val x420 = x4087.<(x65)
              x420
            })
            x421
          })
          {
            val x4089 = x1078
            val x423 = x66.apply(x4089)
            val x425 = x423.PS_SUPPKEY
            val x427 = x1101.get(x425)
            val x615 = x427.nonEmpty
            val x802 = if(x615) 
            {
              val x616 = x427.get
              val x800 = { x617: SUPPLIERRecord => {
                  val x618 = x617.S_SUPPKEY
                  val x619 = x618.==(x425)
                  val x799 = if(x619) 
                  {
                    val x1777 = x617.S_NAME
                    val x1778 = x617.S_ADDRESS
                    val x1779 = x617.S_NATIONKEY
                    val x1780 = x617.S_PHONE
                    val x1781 = x617.S_ACCTBAL
                    val x1782 = x617.S_COMMENT
                    val x1783 = x423.PS_PARTKEY
                    val x1784 = x423.PS_SUPPLYCOST
                    val x623 = x1124.get(x1779)
                    val x711 = x623.nonEmpty
                    val x798 = if(x711) 
                    {
                      val x712 = x623.get
                      val x796 = { x713: NATIONRecord => {
                          val x714 = x713.N_NATIONKEY
                          val x715 = x714.==(x1779)
                          val x795 = if(x715) 
                          {
                            val x1993 = x713.N_NAME
                            val x1994 = x713.N_REGIONKEY
                            val x719 = x1158.get(x1783)
                            val x757 = x719.nonEmpty
                            val x794 = if(x757) 
                            {
                              val x758 = x719.get
                              val x792 = { x759: PARTRecord => {
                                  val x760 = x759.P_PARTKEY
                                  val x761 = x760.==(x1783)
                                  val x791 = if(x761) 
                                  {
                                    val x2102 = x759.P_MFGR
                                    val x2103 = x759.P_TYPE
                                    val x2104 = x759.P_SIZE
                                    val x765 = x1189.get(x1994)
                                    val x778 = x765.nonEmpty
                                    val x790 = if(x778) 
                                    {
                                      val x779 = x765.get
                                      val x788 = { x780: REGIONRecord => {
                                          val x781 = x780.R_REGIONKEY
                                          val x782 = x781.==(x1994)
                                          val x787 = if(x782) 
                                          {
                                            val x2158 = x780.R_NAME
                                            val x783 = REGIONRecord_PARTRecord_NATIONRecord_SUPPLIERRecord_PARTSUPPRecord(x781, x2158, x760, x2102, x2103, x2104, x714, x1993, x1994, x618, x1777, x1778, x1779, x1780, x1781, x1782, x1783, x425, x1784)
                                            val x784 = x783.P_PARTKEY
                                            val x786 = x1204.addBinding(x784, x783)
                                            ()
                                          }
                                          else
                                          {
                                            ()
                                          }
                                          
                                          x787
                                        }
                                      }
                                      val x789 = x779.foreach(x788)
                                      ()
                                    }
                                    else
                                    {
                                      ()
                                    }
                                    
                                    x790
                                  }
                                  else
                                  {
                                    ()
                                  }
                                  
                                  x791
                                }
                              }
                              val x793 = x758.foreach(x792)
                              ()
                            }
                            else
                            {
                              ()
                            }
                            
                            x794
                          }
                          else
                          {
                            ()
                          }
                          
                          x795
                        }
                      }
                      val x797 = x712.foreach(x796)
                      ()
                    }
                    else
                    {
                      ()
                    }
                    
                    x798
                  }
                  else
                  {
                    ()
                  }
                  
                  x799
                }
              }
              val x801 = x616.foreach(x800)
              ()
            }
            else
            {
              ()
            }
            
            val x4489 = x1078
            val x804 = x4489.+(1)
            val x4491 = x1078 = x804
            x4491
          }
          val x820 = { x809: Tuple2[Int, Set[REGIONRecord_PARTRecord_NATIONRecord_SUPPLIERRecord_PARTSUPPRecord]] => {
              val x810 = x809._2
              val x813 = { x811: REGIONRecord_PARTRecord_NATIONRecord_SUPPLIERRecord_PARTSUPPRecord => {
                  val x812 = x811.PS_SUPPLYCOST
                  x812
                }
              }
              val x814 = x810.minBy(x813)
              val x817 = WindowRecord_Int_DynamicCompositeRecord_REGIONRecord_DynamicCompositeRecord_PARTRecord_DynamicCompositeRecord_NATIONRecord_DynamicCompositeRecord_SUPPLIERRecord_PARTSUPPRecord(x814)
              val x819 = x1240.+=(x817)
              ()
            }
          }
          val x821 = x1204.foreach(x820)
          val x859 = while({
            val x4506 = x1241
            val x823 = x4506.unary_!
            val x827 = x823.&&({
              val x825 = x1240.size
              val x826 = x825.!=(0)
              x826
            })
            x827
          })
          {
            val x829 = x1240.head
            val x830 = x1240.-=(x829)
            val x832 = j322
            val x833 = x832.<(100)
            val x834 = x833.==(false)
            val x858 = if(x834) 
            {
              val x4518 = x1241 = true
              x4518
            }
            else
            {
              val x836 = x829.wnd
              val x837 = x836.S_ACCTBAL
              val x838 = x836.S_NAME
              val x839 = x838.string
              val x840 = x836.N_NAME
              val x841 = x840.string
              val x842 = x836.P_PARTKEY
              val x843 = x836.P_MFGR
              val x844 = x843.string
              val x845 = x836.S_ADDRESS
              val x846 = x845.string
              val x847 = x836.S_PHONE
              val x848 = x847.string
              val x849 = x836.S_COMMENT
              val x850 = x849.string
              val x851 = printf("%.2f|%s|%s|%d|%s|%s|%s|%s\n", x837, x839, x841, x842, x844, x846, x848, x850)
              val x852 = j322
              val x853 = x852.+(1)
              val x854 = j322 = x853
              val x4538 = x1270
              val x856 = x4538.+(1)
              val x4540 = x1270 = x856
              x4540
            }
            
            x858
          }
          val x4541 = x1270
          val x861 = printf("(%d rows)\n", x4541)
          ()
        })
        x862
      }
    }
    val x864 = x207.foreach(x863)
    x864
  }
}