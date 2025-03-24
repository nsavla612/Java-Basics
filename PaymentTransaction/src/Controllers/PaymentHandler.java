package Controllers;

import Models.Transaction;
import Models.TransactionSummary;
import Models.TransactionType;
import utils.Constants;

import java.util.*;

public class PaymentHandler {
    static Map<Integer, List<Integer>> amountMap = new HashMap<>();
    static TreeMap<Integer, List<Integer>> peerToMerchantMap = new TreeMap<>();
    private static PaymentHandler instance;
    public static PaymentHandler getInstance() {
        if (instance == null) {
            instance = new PaymentHandler();
        }
        return instance;
    }

    public static TransactionSummary makePayment (int senderId, int amount, TransactionType type ) {

        Transaction transaction = new Transaction.Builder().senderId(senderId).amount(amount).type(type).build();
        amountMap.putIfAbsent(senderId, Arrays.asList(new Integer[]{0, 0, 0}));
        List<Integer> amounts = amountMap.get(senderId);
        switch (type) {
            case TransactionType.PeerToPeer -> amounts.set(0, amounts.get(0) + amount);
            case TransactionType.PeerToMerchant -> amounts.set(1, amounts.get(1) + amount);
            case TransactionType.Self -> amounts.set(2, amounts.get(2) + amount);
        }
        amountMap.put(senderId, amounts);

        boolean isSenderEligibleForReward = checkSenderEligibleForRewards(transaction);
        return new TransactionSummary(transaction, isSenderEligibleForReward);
    }


    private static boolean checkSenderEligibleForRewards(Transaction transaction) {
        if (transaction.getType() != TransactionType.PeerToMerchant) return false;
        int senderId = transaction.getSenderId();
        int transactionAmount = transaction.getAmount();
        int newPeerToMerchantAmount = amountMap.get(senderId).get(1);

        if(amountMap.size() > Constants.PEER_TO_MERCHANT_REWARD_SIZE) {
            if( amountMap.get(transaction.getSenderId()).get(1) <= peerToMerchantMap.firstKey()) return false;

            // Find the amount to be deleted.
            int amountToBeDeleted = newPeerToMerchantAmount - transactionAmount;
            if (!peerToMerchantMap.containsKey(amountToBeDeleted)) {
                amountToBeDeleted = peerToMerchantMap.firstKey();
            }

            //Remove the amountToBeDeleted from map.
            peerToMerchantMap.get(amountToBeDeleted).remove(Integer.valueOf(senderId));
            if (peerToMerchantMap.get(amountToBeDeleted).isEmpty()) {
                peerToMerchantMap.remove(amountToBeDeleted);
            }
        }
        // Add the new amount in the map.
        peerToMerchantMap.putIfAbsent(newPeerToMerchantAmount, new ArrayList<>());
        peerToMerchantMap.get(newPeerToMerchantAmount).add(senderId);
        return true;
    }


    public static int getAllSenderTransactionByType(int senderId, TransactionType transactionType) {
        List<Integer> amounts = amountMap.get(senderId);
        return switch (transactionType) {
            case TransactionType.PeerToPeer -> amounts.get(0);
            case TransactionType.PeerToMerchant -> amounts.get(1);
            case TransactionType.Self -> amounts.get(2);
            default -> -1;
        };
    }

    public static  List<Integer> getAllSenderTransaction(int senderId) {
        return amountMap.get(senderId);
    }

    public static void getAllTransactions() {
        for(Map.Entry<Integer, List<Integer>> entry : amountMap.entrySet()) {
            List<Integer> amounts = entry.getValue();
            System.out.println(" For sender - " + entry.getKey() + "-> Peer to Peer is " + amounts.get(0) + "-> Peer to Merchant is " + amounts.get(1) + "-> Self is " + amounts.get(2));
        }
    }

    public static void clear() {
        amountMap.clear();
        peerToMerchantMap.clear();
    }
}
