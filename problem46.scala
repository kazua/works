//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2046
//K.A

import scala.math._

object problem46 {
    def getOddCmpsNumMin() : String = {
        lazy val pr : Stream[Int] = 2 #:: Stream.from(3).filter(i => pr.takeWhile(j => BigInt(j).pow(2) <= i).forall(i % _ > 0))
        lazy val or : Stream[Int] = Stream.from(2).filter(_ % 2 != 0).filter(i => pr.takeWhile(_ <= i).forall(_ != i))

        if (or.takeWhile(_ <= Int.MaxValue).dropWhile(i => pr.takeWhile(_ <= i).map(j => sqrt((i - j)/2)).forall(k => ceil(k) != floor(k)) == false) != Stream())
            or.takeWhile(_ <= Int.MaxValue).dropWhile(i => pr.takeWhile(_ <= i).map(j => sqrt((i - j)/2)).forall(k => ceil(k) != floor(k)) == false)(0).toString
        else
            "Christian Goldbachの予想はInt型の範囲では正しい"
    }
    def main(args : Array[String]) {
        println(getOddCmpsNumMin())
    }
}