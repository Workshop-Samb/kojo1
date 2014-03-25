/*
 * Copyright (C) 2014 Lalit Pant <pant.lalit@gmail.com>
 *
 * The contents of this file are subject to the GNU General Public License
 * Version 3 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.gnu.org/copyleft/gpl.html
 *
 * Software distributed under the License is distributed on an "AS
 * IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * rights and limitations under the License.
 *
 */

package net.kogics.kojo.picture

import java.awt.AlphaComposite
import java.awt.Color
import java.awt.GradientPaint
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import net.kogics.kojo.core.Picture
import net.kogics.kojo.core.SCanvas
import net.kogics.kojo.util.Utils
import edu.umd.cs.piccolo.PNode
import edu.umd.cs.piccolo.nodes.PImage
import edu.umd.cs.piccolo.util.PPaintContext
import com.jhlabs.image.GaussianFilter
import com.jhlabs.image.LightFilter
import com.jhlabs.image.LightFilter.Light

trait ImageOp {
  def filter(img: BufferedImage): BufferedImage
}

class FadeImageOp(n: Int) extends ImageOp {
  def filter(img: BufferedImage): BufferedImage = {
    val width = img.getWidth(null)
    val height = img.getHeight(null)
    val img2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    val g2 = img2.createGraphics()
    // draw initial image into new image
    g2.drawImage(img, 0, 0, null)
    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN))
    g2.setPaint(new GradientPaint(0, 0, new Color(0.0f, 0.0f, 0.0f, 1.0f), 0, n, new Color(0.0f, 0.0f, 0.0f, 0.0f)))
    g2.fillRect(0, 0, width.toInt, n);
    g2.setPaint(new Color(0.0f, 0.0f, 0.0f, 0.0f))
    g2.fillRect(0, n, width.toInt, height - n);
    g2.dispose()
    img2
  }
}

class BlurImageOp(n: Int) extends ImageOp {
  def filter(img: BufferedImage): BufferedImage = {
    val fltr = new GaussianFilter(n)
    fltr.filter(img, null)
  }
}

class LightImageOp(n: Int) extends ImageOp {
  def filter(img: BufferedImage): BufferedImage = {
    val fltr = new LightFilter
    fltr.addLight(new Light())
    fltr.filter(img, null)
  }
}

trait EffectablePicture extends Picture {
  def fade(n: Int): Unit
  def blur(n: Int): Unit
  def lights(n: Int): Unit
}

class EffectableImagePic(pic: Picture)(implicit val canvas: SCanvas) extends Picture with CorePicOps with CorePicOps2
  with TNodeCacher with RedrawStopper with PicShapeOps with EffectablePicture {
  @volatile var effects = Vector.empty[ImageOp]
  def initGeom(): com.vividsolutions.jts.geom.Geometry = {
    throw new IllegalStateException("Geometry is not available for images")
  }

  def makeTnode: edu.umd.cs.piccolo.PNode = Utils.runInSwingThreadAndPause {
    val node = new PNode
    node.setVisible(false)
    picLayer.addChild(node)
    node
  }

  def pimage(img: BufferedImage) = {
    val inode: PImage = new PImage(img) {
      lazy val picWithEffects: BufferedImage = effects.foldLeft(img) { (imgt, op) => op.filter(imgt) }
      override def paint(paintContext: PPaintContext) {
        val finalImg = picWithEffects
        val g3 = paintContext.getGraphics()
        g3.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR)
        g3.drawImage(finalImg, 0, 0, null)
      }
    }
    inode
  }

  override def realDraw() = {
    pic.draw()
    Utils.runInSwingThread {
      picLayer.removeChild(pic.tnode)
      tnode.addChild(pimage(pic.toImage))
      tnode.translate(pic.bounds.x, pic.bounds.y)
      if (!pic.isInstanceOf[ImagePic]) {
        tnode.translate(-1, 0)
      }
      tnode.setVisible(true)
      tnode.repaint()
    }
  }

  def copy: net.kogics.kojo.core.Picture = new EffectableImagePic(pic.copy)
  def fade(n: Int) {
    effects = effects :+ new FadeImageOp(n)
  }
  def blur(n: Int) {
    effects = effects :+ new BlurImageOp(n)
  }
  def lights(n: Int) {
    effects = effects :+ new LightImageOp(n)
  }
}

abstract class ImageEffect(pic: EffectablePicture) extends EffectablePicture with Transformer {
  val tpic = pic
  def fade(n: Int) = pic.fade(n)
  def blur(n: Int) = pic.blur(n)
  def lights(n: Int) = pic.lights(n)
}

case class Fade(n: Int)(pic: EffectablePicture) extends ImageEffect(pic) {
  def draw() {
    pic.fade(n)
    pic.draw()
  }
  def copy = Fade(n)(pic.copy.asInstanceOf[EffectablePicture])
  override def toString() = s"Fade($n) (Id: ${System.identityHashCode(this)}) -> ${pic.toString}"
}

case class Blur(n: Int)(pic: EffectablePicture) extends ImageEffect(pic) {
  def draw() {
    pic.blur(n)
    pic.draw()
  }
  def copy = Blur(n)(pic.copy.asInstanceOf[EffectablePicture])
  override def toString() = s"Blur ($n) (Id: ${System.identityHashCode(this)}) -> ${pic.toString}"
}

case class Lights(n: Int)(pic: EffectablePicture) extends ImageEffect(pic) {
  def draw() {
    pic.lights(n)
    pic.draw()
  }
  def copy = Lights(n)(pic.copy.asInstanceOf[EffectablePicture])
  override def toString() = s"Lights ($n) (Id: ${System.identityHashCode(this)}) -> ${pic.toString}"
}

abstract class ComposableImageEffect extends Function1[EffectablePicture, EffectablePicture] { outer =>
  def apply(p: EffectablePicture): EffectablePicture
  def ->(p: EffectablePicture) = apply(p)
  def *(other: ComposableImageEffect) = new ComposableImageEffect {
    def apply(p: EffectablePicture): EffectablePicture = {
      ComposableImageEffect.this.apply(other.apply(p))
    }
  }
}

case class Fadec(n: Int) extends ComposableImageEffect {
  def apply(p: EffectablePicture) = Fade(n)(p)
}

case class Blurc(n: Int) extends ComposableImageEffect {
  def apply(p: EffectablePicture) = Blur(n)(p)
}

case class Lightsc(n: Int) extends ComposableImageEffect {
  def apply(p: EffectablePicture) = Lights(n)(p)
}
