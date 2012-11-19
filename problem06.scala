//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%206
//K.A

import scala.math._

object problem06 {
    def sum(acl : Long, xs : List[Int]) : Long = xs match {
        case Nil => acl
        case y :: ys => sum(acl + pow(y,2).toLong, ys)
    }
    def problem06(is : Int) : Long = {
        pow((1 to is).toList.sum, 2).toLong - sum(0, (1 to is).toList)
    }
    def main(args : Array[String]) {
        val ploblem06k = problem06 _
        println(ploblem06k(100))
    }
}