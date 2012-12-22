//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2040
//K.A
 
object problem40 {
    def getIrtlNumMlp(max: Int, irtNum: Seq[Int], acl: Int): Int = max match {
        case n if n < 1 => acl
        case n => getIrtlNumMlp(max / 10, irtNum, acl * irtNum(n - 1))
    }
    def getMaxdgt(numdgt: Int, maxNumdgt: Int, maxNum: Int, acl: BigInt): BigInt = acl match {
        case n if n >= maxNum => (BigInt(10).pow(numdgt - 1)-1) - ((n - maxNum)/(numdgt - 1))
        case n => getMaxdgt(numdgt + 1, maxNumdgt, maxNum, n + (numdgt * (9 * BigInt(10).pow(numdgt - 1))))
    }
    def main(args: Array[String]) {
        val maxMpl = 1000000
        val maxNumdgt = maxMpl.toString.size
        val maxDgt = getMaxdgt(1, maxNumdgt, maxMpl, 0)
        val irtNum = Stream.from(1).takeWhile(_ <= maxDgt).mkString.map(_.toString.toInt)
        println(getIrtlNumMlp(maxMpl, irtNum, 1))
    }
}