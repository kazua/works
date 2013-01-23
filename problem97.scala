//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2097
//K.A

object problem97 {
    def problem97 = (28433 * (BigInt(1) << 7830457) + 1) % BigInt(10).pow(10)
    def main(args : Array[String]) {
        println(problem97)
    }
}