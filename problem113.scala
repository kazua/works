//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%20113
//write kazua

import scala.math._

object problem113 {
    def problem113d(n : Int, k : Int, i : Int = 1, r : BigInt = BigInt(0)) : BigInt = r match {
        case r if r == BigInt(0) => problem113d(n, min(k, n - k), i, BigInt(1))
        case _ => {
            i match {
                case i if i > k => r
                case _ => problem113d(n, k, i + 1, (r * (n - k + i)) / i)
            }
        }
    }
    def problem113 = problem113d(100 + 10, 10) + problem113d(100 + 9, 9) - 10 * 100 - 2
    def main(args : Array[String]) {
        println(problem113)
    }
}