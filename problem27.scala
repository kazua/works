//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2027
//K.A

import scala.collection.mutable._
import scala.math._

object problem27 {
    lazy val pr : Stream[Int] = 2 #:: Stream.from(3).filter(i => pr.takeWhile(j => pow(j, 2) <= i).forall(i % _ > 0)).takeWhile(_ < 1000)
    val memo = Map[Int, Boolean]()
    def isPrime(n : Int) = if (n < 2)
            false
        else if (memo.contains(n))
            memo(n)
        else {
            val b = Iterator.from(2).takeWhile(m => m * m <= n).forall(n % _ != 0)
            memo(n) = b
            b
        }
    def problem27 = (-999 until 1000).filter(_ % 2 != 0).map(i => pr.filter(_ % 2 != 0).map(j => List(i, j, Stream.from(1).takeWhile(k => isPrime(pow(k, 2).toInt + (k * i) + j)).size + 1))).flatten.sortBy(m => m.tail.tail.head).reverse.take(1).map(n => n.head * n.tail.head).max
    def main(args : Array[String]) {
        println(problem27)
    }
}