//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2052
//K.A

object problem52 {
    def getPosInt(mc : Int) : BigInt = {
        Stream.from(1).takeWhile(i => (2 to mc).map(j => (j * i).toString.toList).filter(_.diff(i.toString.toList).size > 0).isEmpty == false).max + 1
    }
    def main(args : Array[String]) {
        println(getPosInt(6))
    }
}