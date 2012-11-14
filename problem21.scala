//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2021
object problem21 {
    def main(args : Array[String]) {
        var amicable = 0
        Range(1,10000).toList.foreach{
            num => {
                val divisora = Range(1,num + 1).toList.filter(num % _ == 0).sum - num
                if (Range(1, divisora + 1).toList.filter(divisora % _ == 0).sum - divisora == num && divisora != num) amicable += 1
            }
        }
        println(amicable)
    }
}