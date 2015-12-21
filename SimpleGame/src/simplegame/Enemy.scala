package simplegame

trait Enemy {
  var maxHealth : Int = 0
  var currentHealth : Int = 0
  var power : Int = 0
  var speedX : Int = 0
  var centerX : Int = 0
  var centerY : Int = 0
  
  private val bg = Entry.getBg1
  
  def update() : Unit = {
    centerX += speedX
    speedX = bg.speedX
  }
  
  def die() : Unit = {}
  
  def attack() : Unit = {}
}