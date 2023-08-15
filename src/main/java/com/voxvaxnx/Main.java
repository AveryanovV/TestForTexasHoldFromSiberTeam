package com.voxvaxnx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        List<PokerHand> hands = new ArrayList<>();
        hands.add(new PokerHand("2C 3C 4C 5C 6C")); //стрит флешь -
        hands.add(new PokerHand("TC TD TH AC KC")); //тройка
        hands.add(new PokerHand("TC TD 3H AC KC")); //пара
        hands.add(new PokerHand("TC TD AH AC KC")); //2 пара
        hands.add(new PokerHand("8C TC JC 9C QC")); //стрит флешь+
        hands.add(new PokerHand("AC AD AH AS 2C")); //каре
        hands.add(new PokerHand("8C 9D TH JS QC")); //стрит
        hands.add(new PokerHand("TH JH QH KH AH")); //роял флешь
        hands.add(new PokerHand("JC JH JD 2H 2C")); //фулл хаус
        hands.add(new PokerHand("TC 5D 3H AC KS")); //старшая карта -
        hands.add(new PokerHand("AC 5D 3H AC KS")); //старшая карта +

        PokerHand.HandComparator handComporator = new PokerHand.HandComparator();

        Collections.sort(hands, handComporator);
        for (PokerHand hand : hands) {
            System.out.println(hand);
        }
    }
}
