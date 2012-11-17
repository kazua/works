//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%207
//K.A

import scala.math._

object problem07 {
    def prime(sl : List[Int], ms : Int) : List[Int] = sl match {
        case Nil => Nil
        case sl => {
            if (sl.head != 2 && (sl.isEmpty || pow(ms, 2) > sl.last)) sl
            else {
                sl.head :: prime(sl.tail.filter(_ % sl.head != 0), sl.head)
            }
        }
    }

    def main(args : Array[String]) {
        val i = 200000
        val cnt = 10001
        println(prime(Range(2, i + 1).toList, 2)(cnt - 1))
    }
}