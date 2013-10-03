//http://projecteuler.net/index.php?section=problems&id=76(英語)
//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2076

//Kazua

import scala.collection.mutable._

object problem76 {
    def problem76(n : Int = 100, p : Seq[Long] = 1L +: Seq.fill(100)(0L), i : Int = 1, j : Int = 1) : Long = i match {
        case i if i <= 99 => {
            j match {
                case j if j > n => problem76(n, p, i + 1, i + 1)
                case _ => {
                    p(j) = p(j) + p(j - i)
                    problem76(n, p, i, j + 1)
                }
            }
        }
        case _ => p(p.length - 1)
    }
    def main(args : Array[String]) {
        println(problem76())
    }
}