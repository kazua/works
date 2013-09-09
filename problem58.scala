//http://projecteuler.net/index.php?section=problems&id=58(英語)
//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2058

//Kazua

import scala.collection.mutable._

object problem58 {
    val m = Map[Int, Boolean]()
    def isPrime(n : Int) =
        if (n < 2)
            false
        else if (m.contains(n))
            m(n)
        else {
            val b = Iterator.from(2).takeWhile(m => m * m <= n).forall(n % _ != 0)
            m(n) = b
            b
        }
    def problem58(p : Int = 8, h : Int = 7 - 1, c : Int = 49, k : Int = 0) : Int = {
        if (p.toDouble / (2 * h + 1) < 0.10) h + 1
        else {
            if (k == 0) problem58(p, h + 2, c, k + 1)
            else {
                if (k == 4) problem58(p, h, c + h, 0)
                else {
                    if (isPrime(c + h)) problem58(p + 1, h, c + h, k + 1)
                    else problem58(p, h, c + h, k + 1)
                }
            }
        }
    }
    def main(args : Array[String]) {
        println(problem58())
    }
}