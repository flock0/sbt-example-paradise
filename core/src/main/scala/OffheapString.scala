package ch.epfl.data
package legobase

import offheap._, x64._

@data class OffheapString(val data: Array[Byte]) {
    def ===(o: OffheapString): Boolean = {
        var eq = true
        if(data.length != o.length)
            eq = false
        var i = 0
        while(eq && i < data.length) {
            eq = data(i) == o(i)
            i += 1
        }
        eq
    }
    def =!=(o: OffheapString): Boolean = !(===(o))
    def apply(i: Int): Byte = data(i)
    def length: Long = data.length
    def string: String = {
        var sb = new StringBuilder
        var i = 0
        while (i < data.length) {
            sb + data(i).toChar
        }
        sb.toString
    }
    
    /* TODO
    override def hashCode(): Int = data.foldLeft(0)((sum, e) => sum + e)
    override def equals(o: Any): Boolean = {
    val arr2 = o.asInstanceOf[OffheapString]
    data.sameElements(arr2.data)
    }
    def startsWith(o: OffheapString): Boolean = data.startsWith(o.data)
    def containsSlice(o: OffheapString): Boolean = data.containsSlice(o.data)
    def endsWith(o: OffheapString): Boolean = data.endsWith(o.data)
    def slice(start: Int, end: Int): OffheapString = OffheapString(data.slice(start, end))
    def indexOfSlice(o: OffheapString, i: Int): Int = data.indexOfSlice(o.data, i)
    def diff(o: OffheapString): Int =
    // TODO: REFACTOR!
    (data zip o.data).foldLeft(0)((res, e) => { if (res == 0) e._1 - e._2 else res })
    def +(o: Any): OffheapString = ??? /* Not used by the shallow implementation */
    override def toString = string
    def zip(o: OffheapString) = data.zip(o.data)
    def foldLeft(c: Int)(f: (Int, Byte) => Int): Int = data.foldLeft(c)(f)
    def reverse(): OffheapString = OffheapString(data.reverse)
    def split(delimiter: Array[Char]): Array[OffheapString] = {
    val str = new String(data.map(_.toChar))
    val res = str.split(delimiter)
    res.map(s => OffheapString(s.getBytes))
    }
    */
}

object OffheapString {
    implicit val m = NativeMemory()
    def apply(data: scala.Array[Byte]): OffheapString = {
        val arr = Array.uninit[Byte](data.size)
        (0 until data.size)foreach { i =>
            arr(i) = data(i)
        }
        OffheapString(arr)
    }
    def default = apply("".getBytes)
}
