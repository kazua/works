//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2025
//K.A

object problem025 {
    def getFbnDgtFst(dc : Int) : BigInt = {
        lazy val fbn: Stream[BigInt] = BigInt(0) #:: fbn.scanLeft(BigInt(1))(_+_).takeWhile(_.toString.length < dc)
        fbn.size
    }
    def main(args : Array[String]) {
        val dc = 1000
        println(getFbnDgtFst(dc))
    }
}