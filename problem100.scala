//Project Euler Problem 100
//箱の中に15個の青い円盤と6個の赤い円盤からなる21個の色のついた円盤が入っていて、無作為に2つ取り出すとき、青い円盤2つを取り出す確率P(BB)は
//P(BB) = (15/21) × (14/20) = 1/2であることがわかる。
//無作為に2つ取り出すとき、青い円盤2つを取り出す確率がちょうど1/2となるような次の組み合わせは、箱が85個の青い円盤と35個の赤い円盤からなるときである。
//箱の中の円盤の合計が1,000,000,000,000を超えるような最初の組み合わせを考える。箱の中の青い円盤の数を求めよ。

//write kazua

object problem100 {
    def problem100(limit : Long, ac : Long = 21, bc : Long = 15) : Long = ac match {
        case a if a > limit => bc
        case _ => problem100(limit, 4 * bc + 3 * ac - 3, 3 * bc + 2 * ac - 2)
    }
    def main(args : Array[String]) {
        println(problem100(1000000000000L))
    }
}