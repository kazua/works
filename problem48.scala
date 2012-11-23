//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2048
//K.A

import scala.math._

object problem48 {
    def getMtpSum(mn : Int) : String = {
        (1 to mn).map(i => BigInt(i).pow(i)).reduceLeft(_+_).toString.takeRight(10)
    }
    def main(args : Array[String]) {
        val mn = 1000
        val gps = getMtpSum _
        println(gps(mn))
    }
}