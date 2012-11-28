//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2050
//K.A

import scala.math._

object problem50 {
    def getPrimeSumMax(mn : Long) : Long = {
        lazy val pr : Stream[Int] = 2 #:: Stream.from(3).filter(i => pr.takeWhile(j => pow(j,2) <= i).forall(i % _ > 0)).takeWhile(_ <= mn)
        val pr2 = pr.takeWhile(i => pr.takeWhile(_ <= i).map(_.toLong).sum <= mn)
        for (i <- pr2.length to 1 by -1) {
            if (pr2.sliding(i).map(_.sum).filter(j => pr.exists(_ == j)).hasNext) return pr2.sliding(i).map(_.sum).filter(j => pr.exists(_ == j)).max
        }
        0
    }
    def main(args : Array[String]) {
        val mn = 1000000
        println(getPrimeSumMax(mn))
    }
}