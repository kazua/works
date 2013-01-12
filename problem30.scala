//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2030
//K.A

object problem30 {
    def getMpNumEqNum(dc : Int) : Int = {
        val mc = (BigInt(10).pow((Stream.from(1).dropWhile(s => BigInt(10).pow(s) - 1 <= (BigInt(10).pow(s) - 1).toString.map(t => BigInt(t.toString).pow(dc)).sum)).take(1).min) - 1).toInt
        (2 to mc).filter(i => i.toString.map(j => BigInt(j.toString.toInt).pow(dc)).sum == i).sum
    }
    def main(args : Array[String]) {
        val dc = 5
        println(getMpNumEqNum(dc))
    }
}