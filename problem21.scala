//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2021
//K.A
object problem21 {
    def problem21(is : Int) : Int = {
        var amicable = 0
        Range(1,is).toList.foreach{
            num => {
                val divisora = Range(1,num + 1).toList.filter(num % _ == 0).sum - num
                if (Range(1, divisora + 1).toList.filter(divisora % _ == 0).sum - divisora == num && divisora != num) amicable += 1
            }
        }
        amicable
    }
    def main(args : Array[String]) {
        val problem21k = problem21 _
        println(problem21k(10000))
    }
}