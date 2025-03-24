package Tests;

import Controllers.PaymentHandler;
import Models.TransactionSummary;
import Models.TransactionType;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentHandlerTest {

    @BeforeEach
    void setUp() {
        PaymentHandler.clear();
    }

    @Test
    void makePaymentForOnePeerToPeer() {
        TransactionSummary summary = PaymentHandler.makePayment(1,100, TransactionType.PeerToPeer);
        assertEquals(100, summary.transaction.getAmount());
        assertEquals(1, summary.transaction.getSenderId());
        assertSame(TransactionType.PeerToPeer, summary.transaction.getType());
        assertFalse(summary.isSenderEligible());
    }

    @Test
    void makePaymentForOneSelf() {
        TransactionSummary summary = PaymentHandler.makePayment(1,100, TransactionType.Self);
        assertEquals(100, summary.transaction.getAmount());
        assertEquals(1, summary.transaction.getSenderId());
        assertSame(TransactionType.Self, summary.transaction.getType());
        assertFalse(summary.isSenderEligible());
    }

    @Test
    void getAllSenderTransaction() {
        PaymentHandler.makePayment(1,100, TransactionType.PeerToMerchant);
        PaymentHandler.makePayment(1,100, TransactionType.PeerToPeer);
        PaymentHandler.makePayment(1,100, TransactionType.Self);
        PaymentHandler.makePayment(1,100, TransactionType.PeerToMerchant);
        PaymentHandler.makePayment(1,50, TransactionType.PeerToPeer);
        assertEquals(200, PaymentHandler.getAllSenderTransactionByType(1 , TransactionType.PeerToMerchant));
        assertEquals(150, PaymentHandler.getAllSenderTransactionByType(1 , TransactionType.PeerToPeer));
    }


    @Test
    void makePaymentForOnePeerToMerchant() {
        TransactionSummary summary = PaymentHandler.makePayment(1,100, TransactionType.PeerToMerchant);
        assertEquals(100, summary.transaction.getAmount());
        assertEquals(1, summary.transaction.getSenderId());
        assertSame(TransactionType.PeerToMerchant, summary.transaction.getType());
        assertTrue(summary.isSenderEligible());
    }

    @Test
    void makePaymentForFivePeerToMerchant() {
        TransactionSummary summary = PaymentHandler.makePayment(1,100, TransactionType.PeerToMerchant);
        assertTrue(summary.isSenderEligible());

        summary = PaymentHandler.makePayment(2,100, TransactionType.PeerToMerchant);
        assertTrue(summary.isSenderEligible());

        summary = PaymentHandler.makePayment(3,100, TransactionType.PeerToMerchant);
        assertTrue(summary.isSenderEligible());

        summary = PaymentHandler.makePayment(4,100, TransactionType.PeerToMerchant);
        assertFalse(summary.isSenderEligible());

        summary = PaymentHandler.makePayment(5,100, TransactionType.PeerToMerchant);
        assertFalse(summary.isSenderEligible());
    }

    @Test
    void makePaymentForFivePeerToMerchantWithRepeatedValues() {
        TransactionSummary summary = PaymentHandler.makePayment(1,100, TransactionType.PeerToMerchant);
        assertTrue(summary.isSenderEligible());

        summary = PaymentHandler.makePayment(2,100, TransactionType.PeerToMerchant);
        assertTrue(summary.isSenderEligible());

        summary = PaymentHandler.makePayment(3,100, TransactionType.PeerToMerchant);
        assertTrue(summary.isSenderEligible());

        summary = PaymentHandler.makePayment(4,100, TransactionType.PeerToMerchant);
        assertFalse(summary.isSenderEligible());

        summary = PaymentHandler.makePayment(4,100, TransactionType.PeerToMerchant);
        assertTrue(summary.isSenderEligible());
    }

    @Test
    void getAllTransactions() {
        PaymentHandler.makePayment(1,100, TransactionType.PeerToMerchant);
        PaymentHandler.makePayment(2,100, TransactionType.PeerToMerchant);
        PaymentHandler.makePayment(3,100, TransactionType.PeerToMerchant);
        PaymentHandler.makePayment(4,100, TransactionType.PeerToMerchant);
        PaymentHandler.makePayment(5,100, TransactionType.PeerToMerchant);
        PaymentHandler.getAllTransactions();
    }
}