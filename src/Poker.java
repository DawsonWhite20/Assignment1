import java.lang.reflect.Array;
import java.util.ArrayList;

class Poker {

    private PlayingCards deck = new PlayingCards();
    ArrayList<String> hand1 = new ArrayList<String>();
    ArrayList<String> hand2 = new ArrayList<String>();

    public Poker()
    {
        PlayingCards decks = deck;
        decks.Shuffle();
        dealHands();
    }

    public Poker(ArrayList<String> handOne, ArrayList<String> handTwo)
    {
        PlayingCards decks = deck;
        hand1 = handOne;
        hand2 = handTwo;
    }

    public void dealHands()
    {
        for(int i = 0;i < 5;i++)
        {
            hand1.add(deck.getNextCard());
            hand2.add(deck.getNextCard());
        }
    }

    public void showHand(int x)
    {
        if(x == 1)
        {
            System.out.println("Player 1's hand:");
            for(int i = 0;i < hand1.size();i++)
            {
                System.out.print(hand1.get(i) + ",");
            }
            System.out.println();
        }
        else
        {
            System.out.println("Player 2's hand:");
            for(int i = 0;i < hand2.size();i++)
            {
                System.out.print(hand2.get(i) + ",");
            }
            System.out.println();
        }
    }

    public int[] countSuite(ArrayList<String> handSuite)
    {
        int[] handArraySuite = new int[4];
        for(int i = 0;i < handSuite.size();i++)
        {
            if(handSuite.get(i).contains("Clubs"))
            {
                handArraySuite[0]++;
            }
            else if(handSuite.get(i).contains("Diamonds"))
            {
                handArraySuite[1]++;
            }
            else if(handSuite.get(i).contains("Hearts"))
            {
                handArraySuite[2]++;
            }
            else if(handSuite.get(i).contains("Spades"))
            {
                handArraySuite[3]++;
            }
        }
        return handArraySuite;
    }

    public int[] countValues(ArrayList<String> handValue)
    {
        int[] handArrayValue = new int[14];
        for(int i = 0;i < handValue.size();i++)
        {
            if(handValue.get(i).contains("A"))
            {
                handArrayValue[1]++;
            }
            else if(handValue.get(i).contains("2"))
            {
                handArrayValue[2]++;
            }
            else if(handValue.get(i).contains("3"))
            {
                handArrayValue[3]++;
            }
            else if(handValue.get(i).contains("4"))
            {
                handArrayValue[4]++;
            }
            else if(handValue.get(i).contains("5"))
            {
                handArrayValue[5]++;
            }
            else if(handValue.get(i).contains("6"))
            {
                handArrayValue[6]++;
            }
            else if(handValue.get(i).contains("7"))
            {
                handArrayValue[7]++;
            }
            else if(handValue.get(i).contains("8"))
            {
                handArrayValue[8]++;
            }
            else if(handValue.get(i).contains("9"))
            {
                handArrayValue[9]++;
            }
            else if(handValue.get(i).contains("10"))
            {
                handArrayValue[10]++;
            }
            else if(handValue.get(i).contains("J"))
            {
                handArrayValue[11]++;
            }
            else if(handValue.get(i).contains("Q"))
            {
                handArrayValue[12]++;
            }
            else if(handValue.get(i).contains("K"))
            {
                handArrayValue[13]++;
            }
        }
        return handArrayValue;
    }

    public int numPairs(int[] countValue)
    {
        int pairs = 0;
        for(int i = 0;i < countValue.length;i++)
        {
            if(countValue[i] == 2)
            {
                pairs++;
            }
        }
        return pairs;
    }

    public int threeOfAKind(int[] countValue)
    {
        for(int i = 0;i < countValue.length;i++)
        {
            if(countValue[i] == 3)
            {
                return countValue[i];
            }
        }
        return 0;
    }

    public int fourOfAKind(int[] countValue)
    {
        for(int i = 0;i < countValue.length;i++)
        {
            if(countValue[i] == 4)
            {
                return countValue[i];
            }
        }
        return 0;
    }

    public boolean fullHouse(int[] countValue)
    {
        int threeOfAKind = threeOfAKind(countValue);
        int pair = numPairs(countValue);
        if(threeOfAKind != 0 && pair == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean straight(int[] countValue)
    {
        int counter = 0;
        for(int i = 0;i < countValue.length;i++)
        {
            if(countValue[i] == 1)
            {
                counter++;
            }
            else
            {
                counter = 0;
            }
            if(counter == 5)
            {
                return true;
            }
        }
        if(countValue[10] == 1 && countValue[11] == 1 && countValue[12] == 1 && countValue[13] == 1 && countValue[1] == 1)
        {
            return true;
        }
        return false;
    }

    public boolean flush(int[] countSuite)
    {
        for(int i = 0;i < countSuite.length;i++)
        {
            if(countSuite[i] == 5)
            {
                return true;
            }
        }
        return false;
    }

    public boolean straightFlush(int[] countValue, int[] countSuite)
    {
        boolean straight = straight(countValue);
        boolean flush = flush(countSuite);
        if(straight == true && flush == true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean royalFlush(int[] countValue, int[] countSuite)
    {
        boolean flush = flush(countSuite);
        int counter = 0;
        boolean royal;
        for(int i = 10;i < countValue.length;i++)
        {
            if(countValue[i] == 1)
            {
                counter++;
            }
            else
            {
                counter = 0;
            }
        }
        if(countValue[1] == 1)
        {
            counter++;
        }
        if(counter == 5)
        {
            royal = true;
        }
        else
        {
            royal = false;
        }
        if(flush == true && royal == true)
        {
            return true;
        }
        return false;
    }

    public String scoreHand(int handNum)
    {
        ArrayList<String> theHand = new ArrayList<String>();
        if(handNum == 1)
        {
            theHand = hand1;
        }
        else if(handNum == 2)
        {
            theHand = hand2;
        }
        int[] suitesCounted = countSuite(theHand);
        int[] valuesCounted = countValues(theHand);
        boolean royalFlush = royalFlush(valuesCounted, suitesCounted);
        if(royalFlush == true)
        {
            return "Royal Flush";
        }
        boolean straightFlush = straightFlush(valuesCounted, suitesCounted);
        if(straightFlush == true)
        {
            return "Straight Flush";
        }
        int fourOfAKind = fourOfAKind(valuesCounted);
        if(fourOfAKind != 0)
        {
            return "4 of a kind";
        }
        boolean fullHouse = fullHouse(valuesCounted);
        if(fullHouse == true)
        {
            return "Full House";
        }
        boolean flush = flush(suitesCounted);
        if(flush == true)
        {
            return "Flush";
        }
        boolean straight = straight(valuesCounted);
        if(straight == true)
        {
            return "Straight";
        }
        int threeOfAKind = threeOfAKind(valuesCounted);
        if(threeOfAKind != 0)
        {
            return "3 of a kind";
        }
        int pairs = numPairs(valuesCounted);
        if(pairs == 2)
        {
            return "2 pairs";
        }
        else if(pairs == 1)
        {
            return "1 pair";
        }
        return "High Card";
    }
}
