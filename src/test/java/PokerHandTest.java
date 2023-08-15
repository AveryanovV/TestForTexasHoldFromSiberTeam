import com.voxvaxnx.PokerHand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PokerHandTest {

    @Test
    @DisplayName("рейтинг покерных комбинаций")
    public void testPokerHandRanking() {

        PokerHand royalFlush = new PokerHand("TH JH QH KH AH"); // Роял флеш
        PokerHand straightFlush = new PokerHand("2D 3D 4D 5D 6D"); // Стрит флеш
        PokerHand fourOfAKind = new PokerHand("7H 7D 7S 7C 3S"); // Каре
        PokerHand fullHouse = new PokerHand("8C 8H 8S 4D 4C"); // Фулл хаус
        PokerHand flush = new PokerHand("2H 4H 6H 8H TH"); // Флеш
        PokerHand straight = new PokerHand("9D TH JH QH KH"); // Стрит
        PokerHand threeOfAKind = new PokerHand("JD JS JC 7H 2C"); // Тройка
        PokerHand twoPair = new PokerHand("5S 5D 9H 9C 2H"); // Две пары
        PokerHand onePair = new PokerHand("AH AC 5S 7D 9C"); // Одна пара
        PokerHand highCard = new PokerHand("QC JH 4D 2S 2C"); // Старшая карта

        PokerHand.HandComparator comparator = new PokerHand.HandComparator();

        // Проверяем, что руки с более сильными картами имеют больший ранг
        assertTrue(comparator.compare(royalFlush, straightFlush) < 0);
        assertTrue(comparator.compare(straightFlush, fourOfAKind) < 0);
        assertTrue(comparator.compare(fourOfAKind, fullHouse) < 0);
        assertTrue(comparator.compare(fullHouse, flush) < 0);
        assertTrue(comparator.compare(flush, straight) < 0);
        assertTrue(comparator.compare(straight, threeOfAKind) < 0);
        assertTrue(comparator.compare(threeOfAKind, twoPair) < 0);
        assertTrue(comparator.compare(twoPair, onePair) < 0);
        assertTrue(comparator.compare(onePair, highCard) < 0);
    }

    @Test
    @DisplayName("сравнение разных рангов")
    public void testSameRankDifferentValues() {
        PokerHand hand1 = new PokerHand("AH KH QH JH TH"); // Роял Флеш
        PokerHand hand2 = new PokerHand("5C 4C 3C 2C AC"); // Стрит Флеш

        PokerHand.HandComparator comparator = new PokerHand.HandComparator();
        int result = comparator.compare(hand1, hand2);

        assertEquals(-1, result, "hand1 должна быть выше по рангу, чем hand2");
    }

    @Test
    @DisplayName("сравнение одинаковых рангов Стрит Флеш")
    public void testHandComparison() {
        PokerHand hand1 = new PokerHand("8C 9C TC JC QC"); // Стрит Флеш
        PokerHand hand2 = new PokerHand("2H 3H 4H 5H 6H"); // Стрит Флеш

        PokerHand.HandComparator comparator = new PokerHand.HandComparator();
        int result = comparator.compare(hand1, hand2);

        assertEquals(-1, result, "hand1 должна быть выше по рангу, чем hand2");
    }

    @Test
    @DisplayName("сравнение одинаковых рангов Стрит")
    public void testEqualHands() {
        PokerHand hand1 = new PokerHand("7D 8D 9D TD JD"); // Стрит
        PokerHand hand2 = new PokerHand("JD TD 9D 8D 7D"); // Стрит

        PokerHand.HandComparator comparator = new PokerHand.HandComparator();
        int result = comparator.compare(hand1, hand2);

        assertEquals(0, result, "hand1 и hand2 должны иметь одинаковый ранг");
    }

    @Test
    @DisplayName("сравнение одинаковых рангов Тройка")
    public void testThreeOfAKind() {
        PokerHand hand1 = new PokerHand("7D 7H 7S 4C 5D"); // Тройка
        PokerHand hand2 = new PokerHand("8D 8H 8S 4C 5D"); // Тройка

        PokerHand.HandComparator comparator = new PokerHand.HandComparator();
        int result = comparator.compare(hand1, hand2);

        assertTrue(result > 0, "hand2 должна быть ниже по рангу, чем hand1");
    }


    @Test
    @DisplayName("сравнение одинаковых рангов Две пары")
    public void testTwoPair() {
        PokerHand hand1 = new PokerHand("6C 6D 2S 2H QS"); // Две пары
        PokerHand hand2 = new PokerHand("7D 7H 2S 2H QS"); // Две пары

        PokerHand.HandComparator comparator = new PokerHand.HandComparator();
        int result = comparator.compare(hand1, hand2);

        assertTrue(result > 0, "hand1 должна быть выше по рангу, чем hand2");
    }

    @Test
    @DisplayName("Определение комбинаций (сравнение с рангом)")
    public void testHandRankThreeOfAKind() {
        PokerHand royalFlush = new PokerHand("TH JH QH KH AH"); // Роял флеш
        PokerHand straightFlush = new PokerHand("2D 3D 4D 5D 6D"); // Стрит флеш
        PokerHand fourOfAKind = new PokerHand("7H 7D 7S 7C 3S"); // Каре
        PokerHand fullHouse = new PokerHand("8C 8H 8S 4D 4C"); // Фулл хаус
        PokerHand flush = new PokerHand("2H 4H 6H 8H TH"); // Флеш
        PokerHand straight = new PokerHand("9D TH JH QH KH"); // Стрит
        PokerHand threeOfAKind = new PokerHand("JD JS JC 7H 2C"); // Тройка
        PokerHand twoPair = new PokerHand("5S 5D 9H 9C 2H"); // Две пары
        PokerHand onePair = new PokerHand("AH AC 5S 7D 9C"); // Одна пара
        PokerHand highCard = new PokerHand("QC JH 4D 2S 9C"); // Старшая карта

        int rankRF = PokerHand.getCardDetails(royalFlush.getHand()).get("rank");
        int rankSF = PokerHand.getCardDetails(straightFlush.getHand()).get("rank");
        int rankFOAK = PokerHand.getCardDetails(fourOfAKind.getHand()).get("rank");
        int rankFH = PokerHand.getCardDetails(fullHouse.getHand()).get("rank");
        int rankF = PokerHand.getCardDetails(flush.getHand()).get("rank");
        int rankS = PokerHand.getCardDetails(straight.getHand()).get("rank");
        int rank3 = PokerHand.getCardDetails(threeOfAKind.getHand()).get("rank");
        int rankTP = PokerHand.getCardDetails(twoPair.getHand()).get("rank");
        int rankOP = PokerHand.getCardDetails(onePair.getHand()).get("rank");
        int rankHC = PokerHand.getCardDetails(highCard.getHand()).get("rank");

        assertEquals(9, rankRF, "Должен быть Роял флеш");
        assertEquals(9, rankSF, "Должен быть Стрит флеш");
        assertEquals(8, rankFOAK, "Должно быть Каре");
        assertEquals(7, rankFH, "Должен быть Фулл хаус");
        assertEquals(6, rankF, "Должен быть Флеш");
        assertEquals(5, rankS, "Должен быть Стрит");
        assertEquals(4, rank3, "Должна быть Тройка");
        assertEquals(3, rankTP, "Должно быть Две пары");
        assertEquals(2, rankOP, "Должна быть Одна пара");
        assertEquals(1, rankHC, "Должна быть Старшая карта");
    }
}

