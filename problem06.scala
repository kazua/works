//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%206
//K.A

import scala.math._

object problem06 {
    def sum(xs : List[Int]) : Long = xs match {
        case Nil => 0
        case y :: ys => pow(y,2).toLong + sum(ys)
    }
    def problem06(is : Int) : Long = {
        pow((1 to is).toList.sum, 2).toLong - sum((1 to is).toList)
    }
    def main(args : Array[String]) {
        val ploblem06k = problem06 _
        println(ploblem06k(100))
    }
}