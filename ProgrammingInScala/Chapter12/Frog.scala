class Frog extends Animal with Philosophical {
    override def toString = "green"
    override def philosophize() {
        println("It ain't easy being "+ toString +"!")
    }
}

object Frog {
    def main(args: Array[String]) = {
        var f = new Frog
        f.philosophize
    }
}
