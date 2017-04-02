///*
// * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
// */
//
//package pl.orbitemobile.wspolnoty.data.remote
//
//import org.junit.Test
//import pl.orbitemobile.kotler.Specification
//import pl.orbitemobile.wspolnoty.data.remote.Parser.Parser
//
//class ParserTest : Specification() {
//
//    val articleHtmlDiv = """<div class="entry-content"><br><br><p style="text-align: center;"><a href="http://wspolnoty-jerozolimskie.pl/wp-content/uploads/2017/08/sr-rosalbafmj.jpg" rel="attachment wp-att-2674"><img class="alignnone size-medium wp-image-2674" src="http://wspolnoty-jerozolimskie.pl/wp-content/uploads/2017/08/sr-rosalbafmj-632x889.jpg" alt="sr-rosalbafmj" width="632" height="889" srcset="http://wspolnoty-jerozolimskie.pl/wp-content/uploads/2017/08/sr-rosalbafmj-632x889.jpg 632w, http://wspolnoty-jerozolimskie.pl/wp-content/uploads/2017/08/sr-rosalbafmj-768x1080.jpg 768w, http://wspolnoty-jerozolimskie.pl/wp-content/uploads/2017/08/sr-rosalbafmj-600x844.jpg 600w, http://wspolnoty-jerozolimskie.pl/wp-content/uploads/2017/08/sr-rosalbafmj.jpg 800w" sizes="(max-width: 632px) 100vw, 632px" /></a></p><br><p style="text-align: center;">Siostry Monastycznych Wspólnot Jerozolimskich, zgromadzone na kapitule generalnej w Magdali (Solonia) pod przewodnictwem bpa Jérôme Beau, biskupa pomocniczego archidiecezji paryskiej, który reprezentował kardynała André Vingt-Trois, z radością informują, że dnia 20 sierpnia 2017 roku, na służbę instytutowi jako przełożoną generalną wybrały siostrę <strong>Rosalbę Bulzaga</strong>, na siedmioletni mandat (według konstytucji Instytutu).</p><br><p style="text-align: center;">Prosimy o modlitwę w jej intencjach.</p><br><p style="text-align: center;">Szczególne podziękowania należą się siostrze <a href="link1">Violaine</a>, która przez ostatnie siedem lat pełniła tę posługę.</p><br><p style="text-align: center;"><a href="https://www.flickr.com/photos/fraternites-jerusalem/sets/72157687960423096">fotorelacja z kapituły generalnej</a></p><br></div>"""
//
//    val expectedStripped = """Siostry Monastycznych Wspólnot Jerozolimskich, zgromadzone na kapitule generalnej w Magdali (Solonia) pod przewodnictwem bpa Jérôme Beau, biskupa pomocniczego archidiecezji paryskiej, który reprezentował kardynała André Vingt-Trois, z radością informują, że dnia 20 sierpnia 2017 roku, na służbę instytutowi jako przełożoną generalną wybrały siostrę Rosalbę Bulzaga, na siedmioletni mandat (według konstytucji Instytutu).<br><br>Prosimy o modlitwę w jej intencjach.<br><br>Szczególne podziękowania należą się siostrze <a href="link1">Violaine</a>, która przez ostatnie siedem lat pełniła tę posługę.<br><br><a href="https://www.flickr.com/photos/fraternites-jerusalem/sets/72157687960423096">fotorelacja z kapituły generalnej</a>"""
//
//    val parser = Parser()
//    @Test
//    fun htmlParserTest() {
//        When
//        val stripped = parser.parse(articleHtmlDiv)
//        Then
//        stripped equals expectedStripped
//    }
//
//    val readingHtml = """ <div class="titles">
//                          <div class="center title-1">
//                           PODRÓŻ DO JEROZOLIMY
//                          </div>
//                          <div class="center title-3">
//                           PIERWSZY OKRES PODRÓŻY
//                          </div>
//                          <div class="center title-4">
//                           Trzej naśladowcy Jezusa*
//                          </div>
//                         </div>
//                         <strong class="cap">9</strong>
//                         <sup> 57 </sup>A gdy szli drogą, ktoś powiedział do Niego: «Pójdę za Tobą, dokądkolwiek się udasz!»
//                         <sup> 58 </sup>Jezus mu odpowiedział: «Lisy mają nory i ptaki powietrzne - gniazda, lecz Syn Człowieczy nie ma miejsca, gdzie by głowę mógł oprzeć».
//                         <sup> 59 </sup>Do innego rzekł: «Pójdź za Mną!» Ten zaś odpowiedział: «Panie, pozwól mi najpierw pójść i pogrzebać mojego ojca!»
//                         <sup> 60 </sup>Odparł mu: «Zostaw umarłym grzebanie ich umarłych, a ty idź i głoś królestwo Boże!»
//                         <sup> 61 </sup>Jeszcze inny rzekł: «Panie, chcę pójść za Tobą, ale pozwól mi najpierw pożegnać się z moimi w domu!»
//                         <sup> 62 </sup>Jezus mu odpowiedział: «Ktokolwiek przykłada rękę do pługa, a wstecz się ogląda, nie nadaje się do królestwa Bożego»*.
//                         <br>
//                         <br>
//                         <br> """
//
//    @Test
//    fun parserTest2() {
//        When
//        val parsed = parser.parseReadingVerse(readingHtml.replace("\n", "").replace("  ", ""))
//        Then
//        println(parsed.second)
//    }
//
//
//}