//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%207
//K.A

import scala.math._

object problem07 {
    def main(args : Array[String]) {
        val cnt = 10001
        val pr = 2 #:: Stream.from(3)
        println(pr.filter(i=> pr.takeWhile(j=> pow(j, 2) <= i).forall(i % _ > 0))(cnt - 1))
    }
}