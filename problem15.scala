//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2015
//K.A

object problem15 {
    def getRootCnt(row: List[Long], clm: Int): Long = {
        val cntLst = row.map(i => row.filter(_ <= i).sum)
        if (clm == 0) cntLst.last else getRootCnt(cntLst, clm - 1)
    }
    def main(args : Array[String]) {
        val rowCnt = 20
        println(getRootCnt((1L to rowCnt + 1).toList, rowCnt - 2))
    }
}