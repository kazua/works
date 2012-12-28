//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2047
//K.A

import scala.math._

object problem47 {
    def getConPfnFst(cnt : Int) : Int = {
        lazy val pr : Stream[Int] = 2 #:: Stream.from(3).filter(i=> pr.takeWhile(j=> BigInt(j).pow(2) <= i).forall(i % _ > 0))
        Stream.from(2).dropWhile(i=> (0 until cnt).map(j => pr.take(ceil(sqrt(i+j)).toInt).filter((i+j) % _ == 0).size).forall(_ == cnt)==false).take(1).min
    }
    def main(args : Array[String]) {
        val cnt = 4
        println(getConPfnFst(cnt))
    }
}