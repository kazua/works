//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%201
//K.A

object problem01 {
    def getMtp(mn : Int) : Int = {
        Range(1, mn).toList.filter(i => i % 3 == 0 || i % 5 == 0).sum
    }
    def main(args : Array[String]) {
        val mn = 1000
        println(getMtp(mn))
    }
}