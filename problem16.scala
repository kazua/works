//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2016
//K.A

import scala.math._

object problem16 {
    def getMtPNum(mp : Int) : String = {
        BigInt(2).pow(mp).toString.map(_.asDigit).sum.toString
    }
    def main(args : Array[String]) {
        val mp = 1000
        val gsn = getMtPNum _
        println(gsn(mp))
    }
}