/*
 * Copyright (C) 2022 June
 *   Bulent Basaran <ben@scala.org> https://github.com/bulent2k2
 *   Lalit Pant <pant.lalit@gmail.com>
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

trait Ne
case object KW extends Ne
case object JKW extends Ne
case object Type extends Ne
case object Val extends Ne
case object Def extends Ne
case object Method extends Ne

case class Name(tr: String, en: String, ne: Ne)

object Data {
  def nType(tr: String, en: String) = Name(tr, en, Type)
  def nVal(tr: String, en: String) = Name(tr, en, Val)
  def nDef(tr: String, en: String) = Name(tr, en, Def)
  def nMet(tr: String, en: String) = Name(tr, en, Method)
  val data = List(
    // type defs, (case) classes
    nType("Nesne", "Object"),
    nType("Birim", "Unit"),
    nType("Her", "Any"),
    nType("HerDeğer", "AnyVal"),
    nType("HerGönder", "AnyRef"),
    nType("Yok", "Null"),
    nType("Hiç", "Nothing"),
    nType("Renk", "Color"),
    nType("Boya", "Paint"),
    nType("Hız", "Speed"),
    nType("Nokta", "Point"),
    nType("Dikdörtgen", "Rectangle"),
    nType("Üçgen", "Triangle2D"),
    nType("İkil", "Boolean"),
    nType("Seçim", "Boolean"),
    nType("Lokma", "Byte"),
    nType("Kısa", "Short"),
    nType("Sayı", "Int"),
    nType("Uzun", "Long"),
    nType("İriSayı", "BigInt"),
    nType("UfakKesir", "Float"),
    nType("Kesir", "Double"),
    nType("İriKesir", "BigDecimal"),
    nType("Harf", "Char"),
    nType("Yazı", "String"),
    nType("EsnekYazı", "collection.mutable.StringBuilder"),
    nType("Belki", "Option"),
    nType("Biri", "Some"),
    nType("Dizi", "collection.Seq"),
    nType("Dizim", "Array"),
    nType("EsnekDizim", "collection.mutable.ArrayBuffer"),
    nType("Dizin", "List"),
    nType("MiskinDizin", "LazyList"),
    nType("Yöney", "Vector"),
    nType("Küme", "Set"),
    nType("Sayılar", "Vector[Sayı]"),
    nType("UzunlukBirimi", "UnitLen"),
    nType("GeoYol", "java.awt.geom.GeneralPath"),
    nType("GeoNokta", "VertexShape"),
    nType("Grafik2B", "scala.swing.Graphics2D"),
    nType("İmge", "Image"),
    nType("Bellekteİmge", "BufferedImage"),
    nType("Bellekteİmgeİşlemi", "java.awt.image.BufferedImageOp"),
    nType("İşlev1", "Function1"),
    nType("İşlev2", "Function2"),
    nType("İşlev3", "Function3"),
    nType("Bölümselİşlev", "PartialFunction"),
    // class defs
    nType("Mp3Çalar", "net.kogics.kojo.music.KMp3"),
    nType("Aralık", "Range"),
    nType("ÇiniDünyası", "net.kogics.kojo.tiles.TileWorld"),
    nType("ÇiniXY", "net.kogics.kojo.tiles.TileXY"),
    nType("BirSayfaKostüm", "net.kogics.kojo.tiles.SpriteSheet"),
    nType("Yöney2B", "Vector2D"),
    nType("Resim", "Picture"),
    nType("İmge", "Image"),
    // defs
    nDef("varMı", "isDefined"),
    nDef("yokMu", "isEmpty"),
    nDef("belirt", "assert"),
    nDef("gerekli", "require"),
    nDef("yeniMp3Çalar", "newMp3Player"),
    nDef("sil", "clear"),
    nDef("silVeSakla", "cleari"),
    nDef("çizimiSil", "clearStepDrawing"),
    nDef("çıktıyıSil", "clearOutput"),
    nDef("silVeÇizimBiriminiKur", "clearWithUL"),
    // val defs, objects, packages
    nVal("yok", "null"),
    nVal("doğru", "true"),
    nVal("yanlış", "false"),
    nVal("yavaş", "slow"),
    nVal("orta", "medium"),
    nVal("hızlı", "fast"),
    nVal("çokHızlı", "superFast"),
    nVal("noktaSayısı", "Pixel"),
    nVal("santim", "Cm"),
    nVal("inç", "Inch"),
    nVal("Boş", "collection.immutable.Nil"),
    nVal("Hiçbiri", "None"),
    nVal("mavi", "blue"),
    nVal("kırmızı", "red"),
    nVal("sarı", "yellow"),
    nVal("yeşil", "green"),
    nVal("mor", "purple"),
    nVal("pembe", "pink"),
    nVal("kahverengi", "brown"),
    nVal("siyah", "black"),
    nVal("beyaz", "white"),
    nVal("renksiz", "noColor"),
    nVal("gri", "gray"),
    nVal("koyuGri", "darkGray"),
    nVal("açıkGri", "lightGray"),
    nVal("turuncu", "orange"),
    nVal("morumsu", "magenta"),
    nVal("camgöbeği", "cyan"),
    // cm
    nVal("renkler", "ColorMaker"),
    // Key Codes
    nVal("tuşlar", "Kc"),
    nDef("Renk", "Color"),
    nDef("artalanıKur", "setBackground"),
    nDef("artalanıKurDik", "setBackgroundV"),
    nDef("artalanıKurYatay", "setBackgroundH"),
    nDef("buAn", "epochTimeMillis"),
    nDef("buSaniye", "epochTime"),
    nDef("yinele", "repeat"),
    nDef("yineleDizinli", "repeati"),
    nDef("yineleDoğruysa", "repeatWhile"),
    nDef("yineleOlanaKadar", "repeatUntil"),
    nDef("yineleKere", "repeatFor"),
    nDef("yineleİçin", "repeatFor"),
    nDef("yineleİlktenSonra", "repeatFor"),
    nDef("satıroku", "readln"),
    nDef("satıryaz", "println"),
    nDef("yaz", "print"),
    nVal("matematik", "math"),
    nDef("piSayısı", "Pi"),
    nDef("eSayısı", "E"),
    nDef("gücü", "pow"),
    nDef("kuvveti", "pow"),
    nDef("karesi", "pow"),
    nDef("karekökü", "sqrt"),
    nDef("onlukTabandaLogu", "log10"),
    nDef("doğalLogu", "log"),
    nDef("logaritması", "log"),
    nDef("sinüs", "sin"),
    nDef("kosinüs", "cos"),
    nDef("tanjant", "tan"),
    nDef("sinüsünAçısı", "asin"),
    nDef("kosinüsünAçısı", "acos"),
    nDef("tanjantınAçısı", "atan"),
    nDef("eüssü", "exp"),
    nDef("radyana", "toRadians"),
    nDef("dereceye", "toDegrees"),
    nDef("taban", "floor"),
    nDef("tavan", "ceil"),
    nDef("yakını", "rint"),
    nDef("işareti", "sign"),
    nDef("sayıya", "toInt"),
    nDef("logTabanlı", "log"),
    nDef("rastgele", "random"),
    nDef("yuvarla", "round"),
    nDef("mutlakDeğer", "abs"),
    nDef("yakın", "round"),
    nDef("enUfağı", "min"),
    nDef("enİrisi", "max"),
    nDef("rastgele", "random"),
    nDef("rastgeleSayı", "randomInt"),
    nDef("rastgeleUzun", "randomLong"),
    nDef("rastgeleKesir", "randomDouble"),
    nDef("rastgeleÇanEğrisinden", "randomNormalDouble"),
    nDef("rastgeleNormalKesir", "randomNormalDouble"),
    nDef("rastgeleDoğalKesir", "randomNormalDouble"),
    nDef("rastgeleTohumunuKur", "initRandomGenerator"),
    nDef("rastgeleİkil", "randomBoolean"),
    nDef("rastgeleSeçim", "randomBoolean"),
    nDef("rastgeleRenk", "randomColor"),
    nDef("rastgeleŞeffafRenk", "randomTransparentColor"),
    nDef("rastgeleDiziden", "randomFrom"),
    nDef("rastgeleKarıştır", "util.Random.shuffle"),
    nDef("evDizini", "homeDir"),
    nDef("buDizin", "currentDir"),
    nDef("kurulumDizini", "installDir"),
    nDef("yazıyüzleri", "availableFontNames"),
    nDef("yazıyüzü", "Font"),
    nDef("yazıÇerçevesi", "textExtent"),
    nDef("kaplumbağa0", "turtle0"),
    nDef("yeniKaplumbağa", "newTurtle"),
    nDef("buradaDur", ""),
    nDef("burdaDur", ""),
    nDef("sayıOku", ""),
    nDef("kesirOku", ""),
    nDef("resimİndir", ""),
    nDef("müzikİndir", ""),
    nDef("müzikMp3üÇal", ""),
    nDef("sesMp3üÇal", ""),
    nDef("müzikMp3üÇalDöngülü", ""),
    nDef("Mp3ÇalıyorMu", ""),
    nDef("Mp3üDurdur", ""),
    nDef("Mp3DöngüsünüDurdur", ""),
    nDef("müzikMp3üÇalıyorMu", ""),
    nDef("müzikÇalıyorMu", ""),
    nDef("müzikMp3üKapat", ""),
    nDef("müzikMp3DöngüsünüKapat", ""),
    nDef("müziğiDurdur", ""),
    nDef("müziğiKapat", ""),
    nDef("kojoVarsayılanBakışaçısınıKur", ""),
    nDef("kojoVarsayılanİkinciBakışaçısınıKur", ""),
    nDef("kojoYazılımcıkBakışaçısınıKur", ""),
    nDef("kojoÇalışmaSayfalıBakışaçısınıKur", ""),
    nDef("kojoÖyküBakışaçısınıKur", ""),
    nDef("kojoGeçmişBakışaçısınıKur", ""),
    nDef("kojoÇıktılıÖyküBakışaçısınıKur", ""),
    nDef("tümEkranÇıktı", ""),
    nDef("tümEkranTuval", ""),
    nDef("tümEkran", ""),
    nDef("tuvalAlanı", "canvasBounds"),
    nDef("eni", ""),
    nDef("boyu", ""),
    nDef("en", ""),
    nDef("boy", ""),
    nDef("yatayMerkezKonumu", ""),
    nDef("dikeyMerkezKonumu", ""),
    nDef("ekranTazelemeHızınıKur", ""),
    nDef("ekranTazelemeHızınıGöster", ""),
    nDef("yaklaş", ""),
    nDef("yaklaşXY", ""),
    nDef("yaklaşmayıSil", ""),
    nDef("yaklaşmayaİzinVerme", ""),
    nDef("tuvaliKaydır", ""),
    nDef("tuvaliDöndür", ""),
    nDef("tuşaBasılıMı", ""),
    nDef("tuşaBasınca", ""),
    nDef("tuşuBırakınca", ""),
    nDef("fareyeTıklıyınca", ""),
    nDef("fareyiSürükleyince", ""),
    nDef("fareKımıldayınca", ""),
    nDef("gridiGöster", ""),
    nDef("gridiGizle", ""),
    nDef("eksenleriGöster", ""),
    nDef("eksenleriGizle", ""),
    nDef("açıÖlçeriGöster", ""),
    nDef("açıÖlçeriGöster", ""),
    nDef("cetveliGöster", ""),
    nDef("çizimiKaydet", ""),
    nDef("çizimiKaydetBoy", ""),
    nDef("çizimiKaydetEn", ""),
    nDef("çizimiPulBoyundaKaydet", ""),
    nDef("artalandaOynat", ""),
    nDef("fareKonumu", ""),
    nDef("yorumla", ""),
    nDef("yineleSayaçla", ""),
    nDef("canlandır", ""),
    nDef("durdur", ""),
    nDef("canlandırmaBaşlayınca", ""),
    nDef("canlandırmaBitince", ""),
    nDef("tuvaliEtkinleştir", ""),
    nDef("yazılımcıkDüzenleyicisiniEtkinleştir", ""),
    nDef("çıktıArtalanınıKur", ""),
    nDef("çıktıYazıRenginiKur", ""),
    nDef("çıktıYazıYüzüBoyunuKur", ""),
    nDef("tuvalBoyutOranınınKur", ""),
    nDef("tuvalBoyutlarınıKur", ""),
    nDef("süreTut", ""),
    nDef("oyunSüresiniGöster", ""),
    nDef("sırayaSok", ""),
    nDef("döndür", "rot"),
    nDef("döndürMerkezli", ""),
    nDef("filtre", ""),
    nDef("gürültü", ""),
    nDef("örgü", ""),
    nDef("büyütXY", ""),
    nDef("saydamlık", ""),
    nDef("ton", ""),
    nDef("parlaklık", ""),
    nDef("aydınlık", ""),
    nDef("yansıtY", ""),
    nDef("yansıtX", ""),
    nDef("eksenler", ""),
    nDef("boyaRengi", ""),
    nDef("kalemRengi", ""),
    nDef("kalemBoyu", ""),
    nDef("çizimÖncesiİşlev", ""),
    nDef("çizimSonrasıİşlev", ""),
    nDef("çevir", ""),
    nDef("yansıt", ""),
    nDef("soluk", ""),
    nDef("bulanık", ""),
    nDef("noktaIşık", ""),
    nDef("sahneIşığı", ""),
    nDef("götür", ""),
    nDef("kaydır", ""),
    nDef("büyüt", ""),
    nDef("ışıklar", ""),
    nDef("birEfekt", ""),
    nDef("NoktaIşık", ""),
    nDef("SahneIşığı", ""),
    nDef("çiz", ""),
    nDef("çizVeSakla", ""),
    nDef("çizMerkezde", ""),
    nDef("çizSahne", ""),
    nDef("çizMerkezdeYazı", ""),
    nDef("merkezeTaşı", ""),
    nDef("sahneKenarındanYansıtma", ""),
    nDef("engeldeYansıtma", ""),
    nDef("imge", ""),
    nDef("imgeNoktası", ""),
    nDef("imgeNoktasınıKur", ""),
    nDef("arayüz", ""),
    nDef("zamanTut", "timeit"),
    nDef("DokumaBoya", "TexturePaint"),
    nDef("boş", "empty"),
    nDef("doldur", "tabulate"),
    nDef("sayalım", "from"),
    nDef("sayıMı", "isDigit"),
    nDef("harfMi", "isLetter"),
    nDef("yazıya", "toString"),
  )

  /*
    def dump = {
        // https://docs.scala-lang.org/overviews/reflection/overview.html
        import scala.reflect.runtime.{ universe => ru }
        // https://www.scala-lang.org/api/2.13.6/scala/util/Try.html
        import scala.util.{ Try, Success, Failure }
        // from: https://stackoverflow.com/questions/33975029/using-scala-reflection-to-check-for-a-method-on-an-object-or-to-find-key-type-on
        def lookUp[T: ru.TypeTag](obj: T, key: String): Option[Any] = ru.typeTag[T].tpe match {
            case ru.TypeRef(a, m, l) if (m.name.toString == "Map" && l.head =:= ru.typeOf[java.lang.String]) => obj.asInstanceOf[Map[String, _]].get(key)
            case ru.TypeRef(a, m, l) if (m.name.toString == "Map" && l.head =:= ru.typeOf[Symbol])           => obj.asInstanceOf[Map[Symbol, _]].get(scala.Symbol(key))
            case _ => Try(obj.getClass.getDeclaredMethod(key).invoke(obj)) match {
                case Success(x) => Some(x)
                case Failure(_) => None
            }
        }

        val hepsi = (for (n <- Data.data) yield n.tr).sorted
        val türler = (for (n <- Data.data if n.ne == Type) yield n.tr).sorted

        çıktıyıSil()
        satıryaz(s"${türler.size} tane Türkçe türümüz var:")
        türler.foreach(satıryaz)
        satıryaz(s"${hepsi.size} tane Türkçe ad var:")
        hepsi.foreach(satıryaz)
    }
   */
}

// Data.dump
