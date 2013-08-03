//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2045
//http://projecteuler.net/index.php?section=problems&id=45

//write kazua

import scala.math._

object problem45 {
    def isPenNum(d : Double) = (sqrt(1.0 + 24.0 * d) + 1.0) / 6.0 == ((sqrt(1.0 + 24.0 * d) + 1.0) / 6.0).toInt
    def problem45(n : Int = 143 + 1, a : Long = 0) : Long = a match {
        case a if isPenNum(a.toDouble) => a
        case _ => problem45(n + 1, n * (2 * n - 1))
    }
    def main(args : Array[String]) {
        println(problem45())
    }
}