//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%204
//K.A

import scala.math._

object problem04 {
    def getPldNum(dig : Int) : Int = {
        val minNum : Int = 1 * pow(10,(dig - 1)).toInt
        val maxNum : Int = (1 * pow(10, dig)).toInt - 1
        (minNum to maxNum).flatMap(i => (minNum to maxNum).map(_ * i)).filter(n => n.toString == n.toString.reverse).max
    }
    def main(args : Array[String]) {
        val dig = 3
        println(getPldNum(dig))
    }
}