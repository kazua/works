//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2056
//K.A

object problem56 {
    def getDigSumMax(mc : Int) : Int = {
        (1 until mc).map(i => (1 until mc).map(j => (BigInt(i).pow(j)).toString.toList.map(_.toString.toInt).sum)).reduceLeft(_ ++ _).max
    }
    def main(args : Array[String]) {
        val mCnt = 100
        println(getDigSumMax(mCnt))
    }
}