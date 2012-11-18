//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%209
//K.A

import scala.math._

object problem09 {
    def pythagoras(mc : Int) : Int = {
        val pg = for (b <- 2 until mc; a <- 1 until b; c = mc - (a + b) if pow(a, 2) + pow(b, 2) == pow(c, 2)) yield a * b * c
        pg.sum
    }
    def main(args : Array[String]) {
        val mc = 1000
        val pgk = pythagoras _

        println(pgk(mc))
    }
}