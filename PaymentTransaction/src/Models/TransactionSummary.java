package Models;

public class TransactionSummary {
    public Transaction transaction;
    boolean isSenderEligible;

    public TransactionSummary() {
       this(null);
    }

    public TransactionSummary(Transaction transaction ) {
        this(transaction, false);
    }

    public TransactionSummary(Transaction transaction,  boolean isSenderEligible ) {
        this.transaction = transaction;
        this.isSenderEligible = isSenderEligible;
    }

    public boolean isSenderEligible() {
        return isSenderEligible;
    }
}
