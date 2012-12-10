//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2015
//K.A

object problem15 {
    def getRootCnt(n: Int, k: Int): BigInt = {
        (BigInt(1) to n).product/((BigInt(1) to k).product * (BigInt(1) to k).product)
    }
    def main(args : Array[String]) {
        val mCnt = 20
        println(getRootCnt(mCnt*2, mCnt))
    }
}