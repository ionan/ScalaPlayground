class Rectangle(val topLeft: Point, val bottomRight: Point) extends Rectangular

object Rectangle {
    def main(args: Array[String]) = {
        var p1 = new Point(15,15)
        var p2 = new Point(25,25)
        var r = new Rectangle(p1,p2)
        println(r.width)
    }
}
