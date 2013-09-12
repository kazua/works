//http://projecteuler.net/index.php?section=problems&id=69(英語)
//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2069

//Kazua

import scala.math._

object problem69 {
    lazy val pr : Stream[Int] = 2 #:: Stream.from(3).filter(i => pr.takeWhile(j => pow(j, 2) <= i).forall(i % _ > 0))
    def problem69(n : Int = 1, i : Int = 0, p : Stream[Int] = pr, m : Int = 1000000) : Int = n match {
        case n if n * p(i) >= m => n
        case _ => problem69(n * p(i), i + 1, p, m)
    }
    def main(args : Array[String]) {
        println(problem69())
    }
}