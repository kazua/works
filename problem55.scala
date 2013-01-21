//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2055
//K.A

object problem55 {
    def bolLychrel(nm : BigInt, acl : Int) : Boolean = nm match {
        case a if (a + BigInt(a.toString.reverse)).toString == (a + BigInt(a.toString.reverse)).toString.reverse => false
        case a => if (acl < 50) bolLychrel(a + BigInt(a.toString.reverse), acl + 1) else true
    }
    def problem55 = (1 until 10000).map(i => bolLychrel(i, 0)).filter(_ == true).size
    def main(args : Array[String]) {
        println(problem55)
    }
}