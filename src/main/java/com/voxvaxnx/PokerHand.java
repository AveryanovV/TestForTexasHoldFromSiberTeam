package com.voxvaxnx;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PokerHand {
    private final String hand;
    private final String ORDER = "23456789TJQKA";
    private static final Map<Character, Integer> faceValues = new HashMap<>();

    public PokerHand(String hand) {
        this.hand = hand;
        int value = 0;
        for (int i = 0; i < ORDER.length(); i++) {
            char face = ORDER.charAt(i);
            faceValues.put(face, value++);
        }
    }

    public String getHand() {
        return hand;
    }

    public static Map<String, Integer> getCardDetails(String hand) {
        Map<Character, Integer> counts = new HashMap<>();
        String[] cards = hand.split(" ");
        List<Character> nominals = new ArrayList<>();
        List<Character> suits = new ArrayList<>();

        // Обработка каждой карты
        for (String card : cards) {
            char nominal = card.charAt(0);
            char suit = card.charAt(1);
            nominals.add(nominal);
            suits.add(suit);
            counts.put(nominal, counts.getOrDefault(nominal, 0) + 1);
        }

        // дубликаты
        Map<Integer, Long> duplicates = counts.values().stream().collect(Collectors.groupingBy(x -> x, Collectors.counting()));

        // Проверка на наличие флеша
        boolean flush = suits.stream().allMatch(suit -> suit == suits.get(0));

        // Сортировка карт для проверки на стрейт
        nominals.sort(Comparator.comparingInt(faceValues::get));

        // Проверка на стрейт
        boolean straight = IntStream.range(0, nominals.size())
                .allMatch(index -> faceValues.get(nominals.get(index)) - (faceValues.get(nominals.get(0))) == index);

        // Вычисление ранга руки на основе комбинаций
        int rank;
        if (flush && straight) {
            rank = 9;
        } else if (duplicates.containsKey(4)) {
            rank = 8;
        } else if (duplicates.containsKey(3) && duplicates.containsKey(2)) {
            rank = 7;
        } else if (flush) {
            rank = 6;
        } else if (straight) {
            rank = 5;
        } else if (duplicates.containsKey(3)) {
            rank = 4;
        } else if (duplicates.get(2) != null && duplicates.get(2) > 1) {
            rank = 3;
        } else if (duplicates.containsKey(2)) {
            rank = 2;
        } else {
            rank = 1;
        }

        // числовое значения руки для дальнейшего сравнения
        StringBuilder valueBuilder = new StringBuilder();
        for (Character face : nominals) {
            valueBuilder.append(Integer.toHexString(faceValues.get(face)));
        }

        // результат с информацией о ранге и числовом значении
        Map<String, Integer> result = new HashMap<>();
        result.put("rank", rank);
        result.put("value", Integer.parseInt(valueBuilder.toString(), 16));
        return result;
    }

    @Override
    public String toString() {
        return "PokerHand " + hand;
    }


    public static class HandComparator implements Comparator<PokerHand> {

        @Override
        public int compare(PokerHand h1, PokerHand h2) {
            Map<String, Integer> handCard1 = getCardDetails(h1.getHand());
            Map<String, Integer> handCard2 = getCardDetails(h2.getHand());

            if (handCard2.get("rank").equals(handCard1.get("rank"))) {
                return handCard2.get("value").compareTo(handCard1.get("value"));
            }
            return handCard2.get("rank").compareTo(handCard1.get("rank"));
        }
    }
}
