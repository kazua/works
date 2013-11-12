//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%20120
//write kazua

object problem120 {
    def problem120 = (3 to 1000).map(i => 2 * i * ((i - 1) / 2)).sum
    def main(args : Array[String]) {
        println(problem120)
    }
}