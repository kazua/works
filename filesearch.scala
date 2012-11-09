import java.io._

object filesearch {
    def filesearch(path : String, schwds : String){
        try{
            new File(path).listFiles.map{
                case d if d.isDirectory() => filesearch(d.getPath(), schwds)
                case f if f.isFile() && f.getName().contains(schwds) => println(f.getPath())
                case e => e
            }
        }catch{
            case ex : java.lang.NullPointerException => println("%s:%s".format(path, "検索不可ディレクトリです"))
            case ex2 : java.io.IOException => println("%s:%s".format(path, "ファイルにアクセスできません"))
        }
    }

    def main(args: Array[String]) {
        filesearch(args(0), args(1))
    }
}