//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2010
//K.A

import scala.math._

object problem10 {
    def getSumPrime(max : Int) : Long = {
        lazy val pr : Stream[Int] = 2 #:: Stream.from(3).filter(i=> pr.takeWhile(j=> pow(j, 2) <= i).forall(i % _ > 0)).takeWhile(_ < max)
        pr.map(_.toLong).reduceLeft(_+_)
    }
    def main(args : Array[String]) {
        val max = 2000000
        val gsp = getSumPrime _
        println(gsp(max))
    }
}