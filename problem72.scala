//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2072
//http://projecteuler.net/index.php?section=problems&id=72

//write kazua

import scala.math._
import scala.collection.mutable._

object problem72 {
    val lmt = 1000000
    val dp = Seq.range(0, lmt + 1)
    def problem72(n : Int = 2, p : Seq[Int] = dp, l : Int = lmt, a : Long = 0) : Long = n match {
        case n if n > l => a
        case _ => {
            if (p(n) == n) chkIdcFracCnt(n, n, p, l)
            problem72(n + 1, p, l, a + p(n))
        }
    }
    def chkIdcFracCnt(n : Int, m : Int, p : Seq[Int] = dp, l : Int) {
        m match {
            case m if m > l => ()
            case _ => {
                p(m) = p(m) / n * (n - 1)
                chkIdcFracCnt(n, m + n, p, l)
            }
        }
    }
    def main(args : Array[String]) {
        println(problem72())
    }
}