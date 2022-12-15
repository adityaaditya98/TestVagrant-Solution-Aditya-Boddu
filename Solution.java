import java.util.*;

public class Solution{

    // Subscription class which represents each newspaper subscription
    static class Subscription{
        String name; // Name of newspaper
        double cost; // Weekly cost of newspaper

        // Constructor
        Subscription(String name, double cost){
            this.name = name;
            this.cost = cost;
        }
    }
    
    static class NewsPaperSubscription{
        
        List<Subscription> weeklySubscriptions;

        // Here we are initializing weekly subscription cost for each newsPaper
        public NewsPaperSubscription(){
            weeklySubscriptions = new ArrayList<>();
    
            weeklySubscriptions.add(new Subscription("TOI", 26.0));
            weeklySubscriptions.add(new Subscription("Hindu", 20.5));
            weeklySubscriptions.add(new Subscription("ET", 34.0));
            weeklySubscriptions.add(new Subscription("BM", 10.5));
            weeklySubscriptions.add(new Subscription("HT", 18.0));
        }
    
        // Method to find List of NewsPaper Subscription combinations within specified budget
        public List<List<String>> getSubscriptionCombinationsForBudget(double budget){
            List<List<String>> result = new ArrayList<>();

            findSubscriptionsInBudget(0, result, new ArrayList<>(), budget);
            return result;
        }

        // Recursive method to find combinations of NewsPaper Subscriptions
        // Time Complexity : 2^N
        // Where N is number of news papers
        void findSubscriptionsInBudget(int pos, List<List<String>> finalSubscriptionsList, List<String> currentSubscriptionsList, double budget){
            int weeklySubscriptionsCount = weeklySubscriptions.size();
            if(pos >= weeklySubscriptionsCount) {
                if(budget >= 0 && currentSubscriptionsList.size() > 1) {
                    finalSubscriptionsList.add(currentSubscriptionsList);
                }
                return;
            }

            findSubscriptionsInBudget(pos+1, finalSubscriptionsList, currentSubscriptionsList, budget);

            List<String> newSubscriptionList = new ArrayList<>(currentSubscriptionsList);
            Subscription currentSubscription = weeklySubscriptions.get(pos);
            newSubscriptionList.add(currentSubscription.name);
            findSubscriptionsInBudget(pos+1, finalSubscriptionsList, newSubscriptionList, budget - weeklySubscriptions.get(pos).cost);
        }
    
    }
    
    // Main Method
    public static void main(String[] args){
        NewsPaperSubscription newsPaperSubscription = new NewsPaperSubscription();

        Scanner in = new Scanner(System.in);
        System.out.print("Enter your budget : ");
        double budget = in.nextDouble();
        List<List<String>> subscriptionCombinations = newsPaperSubscription.getSubscriptionCombinationsForBudget(budget);
        System.out.println("Subscription Combinations : " + subscriptionCombinations);
    }
}