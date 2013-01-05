//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2024
//K.A

object problem24 {
    def getPermNum(sn : String, cnt : Int) : String = {
        val bn = sn.toList.map(_.toString.toInt).filter(_ != 0).product
        sn.toList.permutations.filter(_.mkString.take(1) == (cnt / bn).toString).take(cnt % bn).map(_.mkString).toList.reverse.head
    }
    def main(args : Array[String]) {
        val cnt = 1000000
        val srcNum = "0123456789"
        println(getPermNum(srcNum, cnt))
    }
}