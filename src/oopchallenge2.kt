fun main(args: Array<String>) {
    val myAccount = Account("Joe")
    myAccount.deposit(100)
    myAccount.withdraw(50)
    myAccount.deposit(-20)
    myAccount.withdraw(-2)

    val balance = myAccount.calculateBalance()
    println("Balance is $balance")
    // myAccount.balance = 100

}

class Account(val accountName: String) {
    private var balance = 0
    private var transactions = mutableListOf<Int>()

    fun deposit(amount: Int) {
        if (amount > 0) {
            transactions.add(amount)
            balance += amount
            println("$amount deposited. Balance is now ${this.balance}")
        } else {
            println("Cannot deposit negative sums")
        }
    }

    fun withdraw(withdrawal: Int) {
        if (-withdrawal < 0) {
            transactions.add(-withdrawal)
            this.balance += -withdrawal
            println("$withdrawal withdrawn. Balance is now ${this.balance}")
        } else {
            println("Cannot withdraw negative sum")
        }
    }

    fun calculateBalance(): Int {
        this.balance = 0
        for (transaction in transactions) {
            this.balance += transaction
        }
        return this.balance
    }
}