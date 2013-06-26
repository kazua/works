//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2044
//write kazua

import scala.math._

object problem44 {
    def isPenNum(d : Double) = (sqrt(1.0 + 24.0 * d) + 1.0) / 6.0 == ((sqrt(1.0 + 24.0 * d) + 1.0) / 6.0).toInt
    def problem44(fn : Int, sn : Int, an : Int) : Int = an match {
        case a if a > 0 => a
        case _ => {
            val b = fn * (3 * fn - 1) / 2
            val s = sn * (3 * sn - 1) / 2
            val bs = isPenNum(b - s)
            val ba = isPenNum(b + s)
            if (bs && ba && sn > 0) problem44(fn, sn, b - s)
            else if (!(bs && ba) && sn > 0) problem44(fn, sn - 1, 0)
            else problem44(fn + 1, fn, 0)
        }
    }
    def main(args : Array[String]) {
        println(problem44(1, 0, 0))
    }
}
