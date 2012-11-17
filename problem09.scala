//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%209
//K.A

import scala.math._

object problem09 {

    def main(args : Array[String]) {
        val vc = for (b <- 2 until 1000; a <- 1 until b; c = 1000 - (a - b) if pow(a, 2) + pow(b, 2) == pow(c, 2)) yield a * b * c

        println(vc(0))
    }
}