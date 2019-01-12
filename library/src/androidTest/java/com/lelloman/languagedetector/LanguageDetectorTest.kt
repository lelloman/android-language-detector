package com.lelloman.languagedetector

import android.support.test.InstrumentationRegistry.getContext
import junit.framework.Assert.assertEquals
import org.junit.Test

class LanguageDetectorTest {

    @Test
    fun detectsTestTexts() {
        val detector = LanguageDetectorFactory.make(getContext())

        TEST_CASES.forEach { (language, text) ->
            val prediction = detector
                .detectLanguage(text)
                .maxBy { it.percent }!!
                .let { (language, _) -> language }
            assertEquals(language, prediction)
        }
    }

    private companion object {
        val TEST_CASES = arrayOf(
            DE to "jeden Tag versorgen wir Sie rund um die Uhr mit Nachrichten, Analysen, Kommentaren und Reportagen. Das kostet Geld. Um Ihnen diesen Service bieten zu können, sind wir auf Werbeeinnahmen angewiesen. Wir möchten Sie daher bitten, Ihren Adblocker für FAZ.NET zu deaktivieren.",
            DE to "Sie sehen diese Seite, weil Sie einen Adblocker eingeschaltet haben. Deaktivieren Sie diesen bitte für BILD.de, um unsere Artikel wieder lesen zu können. BILD bietet Ihnen Nachrichten rund um die Uhr. Unsere 500 Reporter berichten für Sie aus aller Welt. Um das zu ermöglichen, sind wir auch auf Werbeeinnahmen angewiesen.",
            EN to "Ceratosaurus was a theropod dinosaur in the Late Jurassic, around 150 million years ago. This genus was first described in 1884 by American paleontologist Othniel Charles Marsh based on a nearly complete skeleton discovered in Garden Park, Colorado, in rocks belonging to the Morrison Formation. In 2000 and 2006, a partial specimen from the Lourinhã Formation of Portugal was described, providing evidence for",
            EN to "Macrozamia riedlei, commonly known as a zamia or zamia palm, is a species of cycad in the plant family Zamiaceae. It is endemic to southwest Australia and often occurs in jarrah forests. It may only attain a height of half a metre or form an above trunk up to two metres with long arching fronds of a similar length.",
            ES to "Anderson había sido internada en un hospital psiquiátrico en 1920, después de que intentara suicidarse en Berlín. Al principio fue registrada con el nombre Fräulein Unbekannt —la versión en alemán para «señorita desconocida»— debido a que rechazó revelar su identidad. Más tarde usaría el apellido Tschaikovsky y luego, Anderson",
            ES to "Explotación infantil se refiere al trabajo de niños en cualquier sistema de producción económica de un país o una región y en el mantenimiento económico de un grupo o clan familiar. La explotación infantil azota en especial a países en vías de desarrollo, pero en ella se ven implicados también países industrializados.",
            FR to "« Gilets jaunes » : en interne comme en externe, des « tensions » traversent BFM-TV",
            FR to "C’est le phénomène à la mode au sein des entreprises : s’occuper du bonheur de ses employés. Cette technique de management, qu’on appelle happiness management (management du bonheur, en français), s’est concrétisée par l’arrivée médiatisée de chief happiness officers (responsables du bonheur), l’installation d’espaces de travail ludiques, voire de baby-foot, toboggans ou salles de sport privées.",
            IT to "Cori razzisti contro i meridionali, qualcuno sostiene (ma il fatto è verificare) in particolare contro i napoletani, sono stati lanciati dagli spalti durante una gara del campionato di calcio di prima categoria tra il Prix Marola e il Montebello, nel vicentino.",
            IT to "Trasportava un carrello per strada stracolmo di ferro, oltre 400 chili, che aveva rubato in un cantiere edile a Battipaglia ma i poliziotti lo hanno intercettato e lo hanno arrestato",
            NL to "De Jemenitische burgeroorlog is een conflict in Jemen dat begon op 19 maart 2015, voortkomend uit de onrust die volgde op de Jemenitische Revolutie in 2011–2012 die de regering van Saleh ten val bracht.",
            NL to "Mensen jagen al op walvissen sinds prehistorische tijden. Archeologische vondsten in Ulsan in Zuid-Korea laten zien dat boeien, harpoenen en lijnen al rond 6000 v.Chr. gebruikt werden om walvissen te bejagen. De oudste bekende manier van het vangen van walvissen is door ze simpel naar de kust te drijven door de weg naar de open oceaan af te snijden met een aantal kleine boten. Dit werd vooral toegepast bij kleine soorten, zoals de griend, witte dolfijn en de narwal",
            PL to "Kilkukrotnie zgłoszony został do zawodów Letniego Pucharu Kontynentalnego 2015, we wszystkich zajmując miejsca poza trzydziestką. W styczniu 2016 został powołany do kadry japońskiej na zawody Pucharu Świata w Zakopanem. 23 stycznia wystąpił w konkursie drużynowym, w którym reprezentacja Japonii zajęła 8. miejsce, natomiast 24 stycznia, w swoim debiucie w zawodach indywidualnych cyklu, Kobayashi zajął 7",
            PL to "George’a nie interesowała księgowość i w 1914 zrezygnował z nauki. Zatrudnił się jako „song plugger”, czyli muzyk prezentujący piosenki na fortepianie, co spotkało się ze zdecydowanym sprzeciwem ojca. Praca ta polegała na graniu nowych piosenek potencjalnym nabywcom wydawnictw nutowych",
            PT to "À mesa nada se desperdiça, tudo se transforma. O bolo-rei reinventa-se numa deliciosa receita com chocolate preto e prolonga o seu reinado durante as festas. Uma surpresa saborosa!",
            PT to "Felizmente não houve vítimas de nenhum tipo, mas ninguém sabe o que aconteceu, até porque as autoridades confirmaram que James Mucciaccio Jr., o condutor deste Ferrari e residente em Palm Beach, não estava sob a influência de nenhuma substância.",
            RU to "Бактериа́льная кле́тка обычно устроена наиболее просто по сравнению с клетками других живых организмов. Бактериальные клетки часто окружает капсула, которая служит защитой от внешней среды. Для многих свободноживущих бактерий характерно наличие жгутиков для передвижения, а также ворсинок.",
            RU to "Однако недалеко от Инвернесса вертолёт попал в густой туман и, двигаясь сквозь него, врезался в гору на острове Малл-оф-Кинтайр (Шотландия) и полностью разрушился. Погибли все 29 человек, находившиеся на его борту. Эта катастрофа стала крупнейшей катастрофой с участием транспорта Королевских ВВС Великобритании, случившейся в мирное время.",
            SV to "Ryktena om att Nordkoreas diktator varit på väg i tåg till Kina bekräftades sent på måndagskvällen av den statliga kinesiska nyhetsbyrån Xinhua. Då hade flera medier rapporterat att det skottsäkra tåg som brukar användas när höga nordkoreanska dignitärer reser till Kina hade setts passera gränsen.",
            SV to "I ett försök att påverka Demokraterna och det amerikanska folket kommer president Donald Trump att på tisdagen tala till nationen.",
            UK to " рід павуків з родини колопрядів. До нього належить понад 80 видів павуків. Великі, яскраво забарвлені самиці більші в декілька разів за дрібних самців. Живляться великими комахами, яких ловлять у великі колоподібні павутинні сітки з характерним павутинним візерунком (стабіліментом). Отрута слабка, для людини безпечні",
            UK to "Розпочав співробітництво з Морзе після того, як його батько, американський промисловець Стів Вейл, зацікавився роботою Морзе і погодився пожертвувати 2 тис. доларів і надати приміщення для дослідів за умови, що Морзе візьме в помічники його сина Альфреда.",
            VI to "Thiên hà Sombrero theo quang phổ hồng ngoại Tốc độ ánh sáng trong chân không, ký hiệu là c, là một hằng số vật lý cơ bản quan trọng trong nhiều lĩnh vực vật lý học. Nó có giá trị chính xác bằng 299.792.458 mét trên giây, bởi vì đơn vị độ dài mét được định nghĩa lại dựa theo hằng số này và giây tiêu chuẩn. Theo thuyết tương đối hẹp, c là tốc độ cực đại mà mọi năng lượng, vật chất,",
            VI to "Ngày lễ giáng sinh được cử hành chính thức vào ngày 25 tháng 12 nhưng thường được mừng từ tối ngày 24 tháng 12 bởi theo lịch Do Thái, thời điểm tính bắt đầu một ngày là lúc hoàng hôn chứ không phải nửa đêm. Lễ chính thức ngày 25 tháng 12 được gọi là \"lễ chính ngày"
        )
    }
}