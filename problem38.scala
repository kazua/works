//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2038
//K.A

object problem38 {
    def problem38 = (1 to 9999).map(i => (1 to 9).map(j => (1 to j).map(k => i * k).mkString)).flatten.filter(n => n.length == 9 && (1 to 9).map(m => n.indexOf(m.toString)).forall(_ >= 0)).max
    def main(args : Array[String]) {
        println(problem38)
    }
}