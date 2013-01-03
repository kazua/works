//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2053
//K.A

object problem53 {
    def getPascNum(lc : Int, mc : Int) : BigInt = {
        (1 to mc).map(i => (1 until mc).map(j => (BigInt(1) to i).product / ((BigInt(1) to j).product * (BigInt(1) to (i - j)).product)).filter(_ > lc).size).reduceLeft(_ + _)
    }
    def main(args : Array[String]) {
        val mCnt = 100
        val lCnt = 1000000
        println(getPascNum(lCnt, mCnt))
    }
}