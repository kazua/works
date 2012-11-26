//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%203
//K.A

import scala.math._

object problem03 {
    def fbnMaxPfc(tn : Long) : Int = {
        val pr = 2 #:: Stream.from(3)
        pr.filter(i=> pr.takeWhile(j=> pow(j, 2) <= i).forall(i % _ > 0)).takeWhile(_ <= sqrt(tn)).filter(tn % _ == 0).max
    }
    def main(args : Array[String]) {
        val tn = 600851475143L
        println(fbnMaxPfc(tn))
    }
}