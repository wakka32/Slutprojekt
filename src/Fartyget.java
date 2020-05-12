public class Fartyget {
   private int [] location;
    private int numOfHits;

    public void setLocations(int [] loc){
        location = loc;
    }

    public String checkGuess(String Userguess){

        String result = "miss";

        int guess = Integer.parseInt(Userguess);

        for (int loc : location){
            if (guess == loc){
            numOfHits++;
            result = numOfHits == location.length ? "träff" : "sänkt";
            break;
        }
    }
    System.out.println(result);

        return result;
    }

}
