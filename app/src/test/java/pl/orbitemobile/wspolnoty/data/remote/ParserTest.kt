/*
 * Copyright (c) 2017. All Rights Reserved. Michal Jankowski orbitemobile.pl
 */

package pl.orbitemobile.wspolnoty.data.remote

import org.junit.Test
import pl.orbitemobile.kotler.Specification
import pl.orbitemobile.wspolnoty.data.remote.HtmlParser.HtmlParser

class ParserTest : Specification() {

    val articleHtmlDiv = """<div class="entry-content"><br><br><p style="text-align: center;"><a href="http://wspolnoty-jerozolimskie.pl/wp-content/uploads/2017/08/sr-rosalbafmj.jpg" rel="attachment wp-att-2674"><img class="alignnone size-medium wp-image-2674" src="http://wspolnoty-jerozolimskie.pl/wp-content/uploads/2017/08/sr-rosalbafmj-632x889.jpg" alt="sr-rosalbafmj" width="632" height="889" srcset="http://wspolnoty-jerozolimskie.pl/wp-content/uploads/2017/08/sr-rosalbafmj-632x889.jpg 632w, http://wspolnoty-jerozolimskie.pl/wp-content/uploads/2017/08/sr-rosalbafmj-768x1080.jpg 768w, http://wspolnoty-jerozolimskie.pl/wp-content/uploads/2017/08/sr-rosalbafmj-600x844.jpg 600w, http://wspolnoty-jerozolimskie.pl/wp-content/uploads/2017/08/sr-rosalbafmj.jpg 800w" sizes="(max-width: 632px) 100vw, 632px" /></a></p><br><p style="text-align: center;">Siostry Monastycznych Wspólnot Jerozolimskich, zgromadzone na kapitule generalnej w Magdali (Solonia) pod przewodnictwem bpa Jérôme Beau, biskupa pomocniczego archidiecezji paryskiej, który reprezentował kardynała André Vingt-Trois, z radością informują, że dnia 20 sierpnia 2017 roku, na służbę instytutowi jako przełożoną generalną wybrały siostrę <strong>Rosalbę Bulzaga</strong>, na siedmioletni mandat (według konstytucji Instytutu).</p><br><p style="text-align: center;">Prosimy o modlitwę w jej intencjach.</p><br><p style="text-align: center;">Szczególne podziękowania należą się siostrze <a href="link1">Violaine</a>, która przez ostatnie siedem lat pełniła tę posługę.</p><br><p style="text-align: center;"><a href="https://www.flickr.com/photos/fraternites-jerusalem/sets/72157687960423096">fotorelacja z kapituły generalnej</a></p><br></div>"""

    val expectedStripped = """Siostry Monastycznych Wspólnot Jerozolimskich, zgromadzone na kapitule generalnej w Magdali (Solonia) pod przewodnictwem bpa Jérôme Beau, biskupa pomocniczego archidiecezji paryskiej, który reprezentował kardynała André Vingt-Trois, z radością informują, że dnia 20 sierpnia 2017 roku, na służbę instytutowi jako przełożoną generalną wybrały siostrę Rosalbę Bulzaga, na siedmioletni mandat (według konstytucji Instytutu).<br><br>Prosimy o modlitwę w jej intencjach.<br><br>Szczególne podziękowania należą się siostrze <a href="link1">Violaine</a>, która przez ostatnie siedem lat pełniła tę posługę.<br><br><a href="https://www.flickr.com/photos/fraternites-jerusalem/sets/72157687960423096">fotorelacja z kapituły generalnej</a>"""

    @Test
    fun htmlParserTest() {
        Given
        val parser = HtmlParser()
        When
        val stripped = parser.parse(articleHtmlDiv)
        Then
        stripped equals expectedStripped
    }

}