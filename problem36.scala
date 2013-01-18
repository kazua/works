//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2036
//K.A

object problem36 {
    def problem36 = (1 until 1000000).filter(i => i.toString == i.toString.reverse && i.toBinaryString == i.toBinaryString.reverse).sum
    def main(args : Array[String]) {
        println(problem36)
    }
}