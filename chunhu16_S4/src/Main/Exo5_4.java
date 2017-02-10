package Main;


import java.util.Random;
import java.util.Scanner;

import lib.BlackjackHand;
import lib.Card;
import lib.Deck;



public class Exo5_4 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		
		Deck deck = new Deck();
		deck.shuffle();
		
		int num = 2 + rand.nextInt(5);
		BlackjackHand BJHand = new BlackjackHand();
		boolean redo = true;
		boolean wrongInput = true;
		boolean win = false;
		
		do{
			if(wrongInput){
				num = 2 + rand.nextInt(5);
				System.out.println("Hand : ");
				BJHand = new BlackjackHand();
				for(int i = 0 ; i < num ; i++){
					Card card = deck.dealCard();
					BJHand.addCard(card);
					System.out.println("  " + card.toString());
				}
				System.out.print("Hand value : ");
				int value = BJHand.getBlackjackValue();
				if(value == 21){
					System.out.println( value + "\nYou won!");
					redo = false;
					win = true;
				} else {
					System.out.println(value);
					System.out.println("Do you want to retry? (o/n)");
				}
			}
			if(!win){
				String s = scan.nextLine();
				if(s.equals("o")){
					wrongInput = true;
					redo = true;
				} else if (s.equals("n")){
					wrongInput = true;
					redo = false;
				} else{
					wrongInput = false;
					System.out.println("Wrong input");
				}
			}
		} while(deck.cardsLeft() >= num && redo && !win);

		if(deck.cardsLeft() < num){
			System.out.println("Not enough cards in the deck");
		}
		System.out.println("End\n\n");
		scan.close();
	}

}
