package simplegame

import java.applet.Applet
import java.awt.Color
import java.awt.Frame
import java.awt.event.KeyListener
import java.awt.event.KeyEvent
import java.awt.Image
import java.awt.Graphics
import java.net.URL

object Entry {
  private val bg1 = new Background(0, 0)
  private val bg2 = new Background(2160, 0)
  
  def getBg1() : Background = bg1
  def getBg2() : Background = bg2
}

class Entry extends Applet with Runnable with KeyListener {
  import Entry._
  
  private var robot : Robot = _
  private var hb : Heliboy = _
  private var hb2 : Heliboy = _
  private var image : Image = _
  private var currentSprite : Image = _
  private var character : Image = _
  private var characterDown : Image = _
  private var characterJumped : Image = _
  private var mybackground : Image = _
  private var heliboy : Image = _
  private var second : Graphics = _
  private var base : URL = _

	override def init(): Unit = {
    robot = new Robot
    setSize(800, 480)
		setBackground(Color.BLACK)
		setFocusable(true)     
		addKeyListener(this)
		val frame = this.getParent.getParent
		frame.asInstanceOf[Frame].setTitle("Q-Bot Alpha")
    try {
      base = getDocumentBase()
    } catch {
       case e : Exception => Unit
    }

    character = getImage(base, "data/character.png")
    characterDown = getImage(base, "data/down.png")
    characterJumped = getImage(base, "data/jumped.png")
    currentSprite = character
    mybackground = getImage(base, "data/background.png")
    heliboy = getImage(base, "data/heliboy.png")
	}

	override def start(): Unit = {
      hb = new Heliboy(340, 360)
      hb2 = new Heliboy(700, 360)
			val thread = new Thread(this)
			thread.start
	}

	override def stop(): Unit = super.stop()

	override def destroy(): Unit = super.destroy()

	override def run(): Unit = {
			while (true) {
        robot.update
        currentSprite = robot match {
          case r if r.jumped => characterJumped
          case r if !r.jumped && !r.ducked => character
          case _ => currentSprite
        }
        
        robot.projectiles.foreach {
          _ match {
            case p if p.visible => p.update
            case p => robot.projectiles -= p
          }
        }
        
        hb.update
        hb2.update
        bg1.update
        bg2.update
				repaint
				try {
					Thread sleep 17
				} catch {
				  case e: InterruptedException => e.printStackTrace()
				}
			}
	}

	override def keyPressed(e : KeyEvent) : Unit = {
			e getKeyCode match {
  			case KeyEvent.VK_UP => Unit
  			case KeyEvent.VK_DOWN => {
           currentSprite = characterDown
            if (!robot.jumped){
                robot.ducked = true
                robot.speedX = 0
            }   
        }
  			case KeyEvent.VK_LEFT => robot moveLeft
  			case KeyEvent.VK_RIGHT => robot moveRight
  			case KeyEvent.VK_SPACE => robot jump
        case KeyEvent.VK_CONTROL => {
           if (!robot.ducked && !robot.jumped) {
              robot.shoot
          }
        }
  			case _ => Unit
			}
	}
  
  override def keyReleased(e : KeyEvent) : Unit = {
    e getKeyCode match {
      case KeyEvent.VK_UP => Unit
      case KeyEvent.VK_DOWN => {
        currentSprite = character
        robot.ducked = false
      }
      case KeyEvent.VK_LEFT => robot stopLeft
      case KeyEvent.VK_RIGHT => robot stopRight
      case KeyEvent.VK_SPACE => Unit
      case _ => Unit
    }
  }
  
  override def keyTyped(e : KeyEvent) : Unit = Unit
  
  override def update(g : Graphics) : Unit = {
    if (image == null) {
      image = createImage(this getWidth, this getHeight)
      second = image getGraphics
    }

    second setColor(getBackground())
    second fillRect(0, 0, getWidth, getHeight)
    second setColor(getForeground)
    paint(second)

    g drawImage(image, 0, 0, this)
  }
  
  override def paint(g : Graphics) : Unit = {
    g drawImage(mybackground, bg1 bgX, bg1 bgY, this)
    g drawImage(mybackground, bg2.bgX, bg2 bgY, this)
    
    robot.projectiles.foreach(p => {
      g.setColor(Color.YELLOW)
      g.fillRect(p.x, p.y, 10, 5)
    })
    
    g drawImage(currentSprite, robot.centerX - 61, robot.centerY - 63, this)
    g drawImage(heliboy, hb.centerX - 48, hb.centerY - 48, this)
    g drawImage(heliboy, hb2.centerX - 48, hb2.centerY - 48, this)
  }
}