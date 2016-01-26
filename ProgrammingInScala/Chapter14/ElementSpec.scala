import org.scalatest.FunSpec
import Element.elem

class ElementSpec extends FunSpec {

    describe("A UniformElement") {

         it("should have a width equal to the passed value") {
             val ele = elem('x', 2, 3)
             assert(ele.width === 2)
         }

         it("should have a height equal to the passed value") {
             val ele = elem('x', 2, 3)
             assert(ele.height === 3)
         }

         it("should throw an IAE if passed a negative width") {
             intercept(classOf[IllegalArgumentException]) {
                 elem('x', -2, 3)
             }
         }
   }
}

object ElementSpec {
    def main(String: Array[String]) = {
        (new ElementSpec).execute()
    }
}
