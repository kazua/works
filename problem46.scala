//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2046
//K.A

import scala.math._

object problem46 {
    def getOddCmpsNumMin() : Int = {
        lazy val pr : Stream[Int] = 2 #:: Stream.from(3).filter(i => pr.takeWhile(j => BigInt(j).pow(2) <= i).forall(i % _ > 0))
        val or : Stream[Int] = Stream.from(2).filter(_ % 2 != 0).filter(i => pr.takeWhile(_ <= i).forall(_ != i))
        val ormin : Stream[Int] = or.takeWhile(_ <= Int.MaxValue).dropWhile(i => pr.takeWhile(_ <= i).map(j => sqrt((i - j)/2)).forall(k => ceil(k) != floor(k)) == false)

        ormin.take(1).min
    }
    def main(args : Array[String]) {
        println(getOddCmpsNumMin())
    }
}