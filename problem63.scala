//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2063
//K.A

import scala.math._

object problem63 {
    def problem63 = (1 until 10).map(i => (log(10D) / log(10D / i)).toInt).sum
    def main(args : Array[String]) {
        println(problem63)
    }
}