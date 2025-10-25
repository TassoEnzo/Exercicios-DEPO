import java.util.HashMap;
import java.util.Map;

interface DepositUI {
    double requestDepositAmount();
    void informDepositSuccess(double amount, double newBalance);
}

interface WithdrawalUI {
    double requestWithdrawalAmount();
    void informInsufficientFunds(double requested, double available);
    void informWithdrawalSuccess(double amount, double newBalance);
}

interface TransferUI {
    double requestTransferAmount();
    String requestTargetAccountId();
    void informTransferSuccess(double amount, String targetAccountId, double newBalance);
    void informTransferFailed(String reason);
}

interface PayBillUI {
    double requestBillAmount();
    String requestBiller();
    void informPaymentSuccess(String biller, double amount, double newBalance);
    void informPaymentFailed(String reason);
}

abstract class Transaction {
    public abstract void execute();
}

class Account {
    private final String id;
    private double balance;

    public Account(String id, double initial) {
        this.id = id;
        this.balance = initial;
    }

    public String getId() { return id; }

    public synchronized double getBalance() {
        return balance;
    }

    public synchronized void deposit(double amount) {
        if (amount < 0) throw new IllegalArgumentException("Amount negative");
        balance += amount;
    }

    public synchronized boolean withdraw(double amount) {
        if (amount < 0) throw new IllegalArgumentException("Amount negative");
        if (amount > balance) return false;
        balance -= amount;
        return true;
    }
}

class DepositTransaction extends Transaction {
    private final DepositUI ui;
    private final Account account;

    public DepositTransaction(DepositUI ui, Account account) {
        this.ui = ui;
        this.account = account;
    }

    @Override
    public void execute() {
        double amount = ui.requestDepositAmount();
        account.deposit(amount);
        ui.informDepositSuccess(amount, account.getBalance());
    }
}

class WithdrawalTransaction extends Transaction {
    private final WithdrawalUI ui;
    private final Account account;

    public WithdrawalTransaction(WithdrawalUI ui, Account account) {
        this.ui = ui;
        this.account = account;
    }

    @Override
    public void execute() {
        double amount = ui.requestWithdrawalAmount();
        if (!account.withdraw(amount)) {
            ui.informInsufficientFunds(amount, account.getBalance());
        } else {
            ui.informWithdrawalSuccess(amount, account.getBalance());
        }
    }
}

class TransferTransaction extends Transaction {
    private final TransferUI ui;
    private final Account source;
    private final Map<String, Account> accountDirectory;

    public TransferTransaction(TransferUI ui, Account source, Map<String, Account> accountDirectory) {
        this.ui = ui;
        this.source = source;
        this.accountDirectory = accountDirectory;
    }

    @Override
    public void execute() {
        double amount = ui.requestTransferAmount();
        String targetId = ui.requestTargetAccountId();
        Account target = accountDirectory.get(targetId);
        if (target == null) {
            ui.informTransferFailed("Target account not found: " + targetId);
            return;
        }
        synchronized (this) {
            if (!source.withdraw(amount)) {
                ui.informTransferFailed("Insufficient funds. Available: " + source.getBalance());
                return;
            }
            target.deposit(amount);
        }
        ui.informTransferSuccess(amount, targetId, source.getBalance());
    }
}

class PayGasBillTransaction extends Transaction {
    private final PayBillUI ui;
    private final Account account;

    public PayGasBillTransaction(PayBillUI ui, Account account) {
        this.ui = ui;
        this.account = account;
    }

    @Override
    public void execute() {
        double amount = ui.requestBillAmount();
        String biller = ui.requestBiller();
        if (!account.withdraw(amount)) {
            ui.informPaymentFailed("Insufficient funds to pay " + biller + ". Available: " + account.getBalance());
        } else {
            ui.informPaymentSuccess(biller, amount, account.getBalance());
        }
    }
}

class SimpleDepositUI implements DepositUI {
    private final double amount;

    public SimpleDepositUI(double amount) { this.amount = amount; }

    @Override
    public double requestDepositAmount() { return amount; }

    @Override
    public void informDepositSuccess(double amount, double newBalance) {
        System.out.printf("[DepositUI] Depósito de %.2f realizado. Novo saldo: %.2f%n", amount, newBalance);
    }
}

class SimpleWithdrawalUI implements WithdrawalUI {
    private final double amount;

    public SimpleWithdrawalUI(double amount) { this.amount = amount; }

    @Override
    public double requestWithdrawalAmount() { return amount; }

    @Override
    public void informInsufficientFunds(double requested, double available) {
        System.out.printf("[WithdrawalUI] Falha: saldo insuficiente. Solicitado: %.2f, Disponível: %.2f%n",
                          requested, available);
    }

    @Override
    public void informWithdrawalSuccess(double amount, double newBalance) {
        System.out.printf("[WithdrawalUI] Saque de %.2f realizado. Novo saldo: %.2f%n", amount, newBalance);
    }
}

class SimpleTransferUI implements TransferUI {
    private final double amount;
    private final String targetId;

    public SimpleTransferUI(double amount, String targetId) {
        this.amount = amount;
        this.targetId = targetId;
    }

    @Override
    public double requestTransferAmount() { return amount; }

    @Override
    public String requestTargetAccountId() { return targetId; }

    @Override
    public void informTransferSuccess(double amount, String targetAccountId, double newBalance) {
        System.out.printf("[TransferUI] Transferência de %.2f para %s realizada. Saldo atual: %.2f%n",
                          amount, targetAccountId, newBalance);
    }

    @Override
    public void informTransferFailed(String reason) {
        System.out.printf("[TransferUI] Transferência falhou: %s%n", reason);
    }
}

class SimplePayBillUI implements PayBillUI {
    private final double amount;
    private final String biller;

    public SimplePayBillUI(double amount, String biller) {
        this.amount = amount;
        this.biller = biller;
    }

    @Override
    public double requestBillAmount() { return amount; }

    @Override
    public String requestBiller() { return biller; }

    @Override
    public void informPaymentSuccess(String biller, double amount, double newBalance) {
        System.out.printf("[PayBillUI] Pagamento ao %s no valor de %.2f realizado. Novo saldo: %.2f%n",
                          biller, amount, newBalance);
    }

    @Override
    public void informPaymentFailed(String reason) {
        System.out.printf("[PayBillUI] Pagamento falhou: %s%n", reason);
    }
}

public class AtmIspExample {
    public static void main(String[] args) {
        Account acc1 = new Account("A1", 1000.0);
        Account acc2 = new Account("A2", 200.0);

        Map<String, Account> directory = new HashMap<>();
        directory.put(acc1.getId(), acc1);
        directory.put(acc2.getId(), acc2);

        System.out.println("Saldo inicial A1: " + acc1.getBalance());
        System.out.println("Saldo inicial A2: " + acc2.getBalance());
        System.out.println("-----");

        DepositUI depUI = new SimpleDepositUI(150.0);
        Transaction dep = new DepositTransaction(depUI, acc1);
        dep.execute();

        WithdrawalUI withUI = new SimpleWithdrawalUI(50.0);
        Transaction with = new WithdrawalTransaction(withUI, acc1);
        with.execute();

        TransferUI transferUI = new SimpleTransferUI(300.0, "A2");
        Transaction transfer = new TransferTransaction(transferUI, acc1, directory);
        transfer.execute();

        PayBillUI payUI = new SimplePayBillUI(200.0, "GasCompany");
        Transaction payBill = new PayGasBillTransaction(payUI, acc1);
        payBill.execute();

        System.out.println("-----");
        System.out.printf("Saldo final A1: %.2f%n", acc1.getBalance());
        System.out.printf("Saldo final A2: %.2f%n", acc2.getBalance());
    }
}