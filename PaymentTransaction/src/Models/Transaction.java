package Models;

import java.util.UUID;

public class Transaction {
    private int senderId;
    private TransactionType type;
    int amount;
    private UUID transactionId;

    public Transaction() {
    }

    public Transaction(Builder builder) {
        this.senderId = builder.senderId;
        this.amount = builder.amount;
        this.type = builder.type;
        this.transactionId = UUID.randomUUID();
    }

    public TransactionType getType() {
        return type;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return " id = " + transactionId + " sender= " + senderId + " amount= " + amount + " type= " + type;
    }

    public static class Builder {
        private int senderId;
        private TransactionType type;
        int amount;
        public Builder() {}

        public Builder senderId(int senderId) {
            this.senderId = senderId;
            return this;
        };

        public Builder amount(int amount) {
            this.amount = amount;
            return this;
        };

        public Builder type(TransactionType type) {
            this.type = type;
            return this;
        };

        public Transaction build()
        {
            return new Transaction(this);
        }

    }

}
