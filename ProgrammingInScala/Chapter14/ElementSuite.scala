import org.scalatest.Suite
import Element.elem

class ElementSuite extends Suite {

    def testUniformElement() {
        val ele = elem('x', 2, 3)
        assert(ele.width == 2)
    }

    def testUniformElement_wrong() {
        val ele = elem('x', 2, 3)
        assert(ele.width == 7)
    }

    def otherTests(){
        val ele = elem('x', 2, 3)
        expect(2) {
            ele.width
        }
        intercept(classOf[IllegalArgumentException]) {
            elem('x', -2, 3)
        }
    }
}

object ElementSuite {
    def main(String: Array[String]) = {
        (new ElementSuite()).execute()
    }
}
