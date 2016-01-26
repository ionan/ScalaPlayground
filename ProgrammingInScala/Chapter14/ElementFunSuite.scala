import org.scalatest.FunSuite
import org.scalacheck.Prop._
import Element.elem

class ElementFunSuite extends FunSuite {

    test("elem result should have passed width") {
        val ele = elem('x', 2, 3)
        assert(ele.width == 2)
    }

    test("elem result should have passed width", (w: Int) =>
      w > 0 ==> (elem('x', w, 3).width == w)
    )

    test("elem result should have passed height", (h: Int) =>
      h > 0 ==> (elem('x', 2, h).height == h)
    )
}

object ElementFunSuite {
    def main(String: Array[String]) = {
        (new ElementFunSuite()).execute()
    }
}
