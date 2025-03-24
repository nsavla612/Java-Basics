import Controllers.PaymentHandler;
import Models.TransactionSummary;
import Models.TransactionType;

public class Main {
    public static void main(String[] args) {
        TransactionSummary summary;

        summary = PaymentHandler.makePayment(1 , 100, TransactionType.Self);
        System.out.println(" sender id 1 is eligible for rewards- " + summary.isSenderEligible());

        summary = PaymentHandler.makePayment(2 , 100, TransactionType.PeerToMerchant);
        System.out.println(" sender id 2 is eligible for rewards- " + summary.isSenderEligible());

        summary = PaymentHandler.makePayment(3 , 100, TransactionType.PeerToPeer);
        System.out.println(" sender id 3 is eligible for rewards- " + summary.isSenderEligible());

        summary = PaymentHandler.makePayment(4 , 120, TransactionType.PeerToMerchant);
        System.out.println(" sender id 4 is eligible for rewards- " + summary.isSenderEligible());

        summary = PaymentHandler.makePayment(5 , 150, TransactionType.PeerToMerchant);
        System.out.println(" sender id 5 is eligible for rewards- " + summary.isSenderEligible());

        summary = PaymentHandler.makePayment(6 , 50, TransactionType.PeerToMerchant);
        System.out.println(" sender id 6 is eligible for rewards- " + summary.isSenderEligible());

        summary = PaymentHandler.makePayment(6 , 150, TransactionType.PeerToMerchant);
        System.out.println(" sender id 6 is eligible for rewards- " + summary.isSenderEligible());

        PaymentHandler.getAllTransactions();
        System.out.println( "Total Peer to Merchant amount of sender 2 - " +   PaymentHandler.getAllSenderTransactionByType(2 , TransactionType.PeerToMerchant));
        System.out.println( "Total Peer to Merchant amount of sender 6 - " +   PaymentHandler.getAllSenderTransactionByType(6 , TransactionType.PeerToMerchant));


    }
}