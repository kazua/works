//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2012
//K.A

import scala.math._

object problem12 {
    def getTrgFst(mn : Int) : Int = {
        lazy val tn: Stream[Int] = 1 #:: Stream.from(2).scanLeft(1)(_+_)
        def divCnt(tn: Int) = (0 to Int.MaxValue -1).takeWhile(i => pow(i, 2) <= tn).reduceLeft((l, r) => if(tn % r == 0) l + 2 else l)

        tn.find(divCnt(_) >= mn).min
    }
    def main(args : Array[String]) {
        val mn = 500
        println(getTrgFst(mn))
    }
}