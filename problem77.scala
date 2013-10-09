//http://projecteuler.net/index.php?section=problems&id=77(英語)
//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2077

//Kazua

import scala.collection.mutable._
import scala.math._

object problem77 {
    lazy val pr : Stream[Int] = 2 #:: Stream.from(3).filter(i => pr.takeWhile(j => pow(j, 2) <= i).forall(i % _ > 0)).takeWhile(_ < 5000)
    def problem77(n : Int = 2, t : Int = 0) : Int = t match {
        case t if t > 5000 => n - 1
        case _ => problem77(n + 1, problem77d(n, 0, 1 +: Seq.fill(n)(0)))
    }
    def problem77d(n : Int, p : Int, w : Seq[Int]) : Int = p match {
        case p if p == pr.length || pr(p) > n => w(n)
        case _ => {
            for (i <- pr(p) to n) w(i) += w(i - pr(p))
            problem77d(n, p + 1, w)
        }
    }
    def main(args : Array[String]) {
        println(problem77())
    }
}