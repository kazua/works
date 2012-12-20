//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2040
//K.A

object problem40 {
    def getIrtlNum(cnt : Int): Int = {
        val irtNum = (1 to 200000).mkString.toArray.map(_.toString.toInt)
        irtNum(cnt).toInt
    }
    def getIrtlNumMlp(max : Int,acl : Int): Int = max match {
        case n if n < 1 => acl
        case n => getIrtlNumMlp(max/10,acl*getIrtlNum(n-1))
    }
    def main(args : Array[String]) {
        println(getIrtlNumMlp(1000000,1))
    }
}