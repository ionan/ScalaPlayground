object Animals {

  class Food

  abstract class Animal {
    type SuitableFood <: Food
    def eat(food: SuitableFood)
  }

  class Grass extends Food

  class Cow extends Animal {
    type SuitableFood = Grass
    override def eat(food: Grass) {}
  }

  class Fish extends Food

  class DogFood extends Food

  class Dog extends Animal {
    type SuitableFood = DogFood
    override def eat(food: DogFood) {}
  }

  def main(args: Array[String]){
    val bessy: Animal = new Cow
    val lassie = new Dog
    val bootsie = new Dog

    lassie eat (new bootsie.SuitableFood)
    lassie eat (new bessy.SuitableFood)
    bessy eat (new Fish)
  }

}