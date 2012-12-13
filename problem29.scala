//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2029
//K.A

object problem29 {
    def getDtntSize(min: Int, max: Int): Int = {
        (min to max).map(i => (BigInt(min) to max).map(j=> j.pow(i))).toList.reduceLeft(_ ++ _).distinct.size
    }
    def main(args : Array[String]) {
        val min = 2
        val max = 100
        println(getDtntSize(min, max))
    }
}